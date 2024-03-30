package community.notificationserver.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import community.notificationserver.entity.DeviceDetails;
import community.notificationserver.entity.DeviceInfoEntity;
import community.notificationserver.entity.UserDetails;
import community.notificationserver.entity.UserInfoEntity;
import community.notificationserver.mapper.DeviceMapper;
import community.notificationserver.model.EmailDeviceAndContentDetails;
import community.notificationserver.model.RequiredEmailContentEvent;
import community.notificationserver.repository.DeviceInfoRepository;
import community.notificationserver.model.EmailRequestBody;
import community.notificationserver.repository.UserInfoRepository;
import community.notificationserver.service.EmailNotificationService;
import community.notificationserver.service.ManagementService;
import community.notificationserver.utility.NotificationUtility;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static community.notificationserver.constant.NotificationConstants.TOPIC_EMAIL_NOTIFICATION;

@Service
public class EmailNotificationServiceImpl implements EmailNotificationService {

  private final KafkaTemplate<String, String> kafkaTemplate;
  private final RedisCacheService redisCacheService;
  private final DeviceInfoRepository deviceInfoRepository;
  private final UserInfoRepository userInfoRepository;
  private final ManagementService managementService;
  private final DeviceMapper deviceMapper;

  public EmailNotificationServiceImpl(
          KafkaTemplate<String, String> kafkaTemplate,
          RedisCacheService redisCacheService,
          DeviceInfoRepository deviceInfoRepository, UserInfoRepository userInfoRepository, ManagementService managementService, DeviceMapper deviceMapper) {
    this.kafkaTemplate = kafkaTemplate;
    this.redisCacheService = redisCacheService;
    this.deviceInfoRepository = deviceInfoRepository;
    this.userInfoRepository = userInfoRepository;
      this.managementService = managementService;
      this.deviceMapper = deviceMapper;
  }

  @Override
  @Transactional
  public boolean publishNotification(EmailRequestBody emailRequestBody) {
    List<String> recipientUserIds =
        emailRequestBody.getTo().stream().map(EmailRequestBody.Recipient::getUserId).toList();
    List<String> unavailableUserIds = new ArrayList<>();
    List<UserDetails> userDetailsList = new ArrayList<>();
    for (String userId : recipientUserIds) {
      Object details = redisCacheService.getValue(userId);
      if (details != null) {
        if(details instanceof UserDetails) {
          UserDetails dd = (UserDetails) details;
          userDetailsList.add(dd);
        }
      } else {
        unavailableUserIds.add(userId);
      }
    }
    if (!unavailableUserIds.isEmpty()) {
      Collection<UserInfoEntity> userInfoEntities =
              userInfoRepository.findAllByUserIdIn(unavailableUserIds);

      for (UserInfoEntity userInfoEntity : userInfoEntities) {
        UserDetails userDetails =  deviceMapper.entityToUserDto(userInfoEntity);
        userDetailsList.add(userDetails);
      }
    }

    return createMessagePayload(emailRequestBody, userDetailsList);
  }

  private boolean createMessagePayload(EmailRequestBody emailRequestBody, List<UserDetails> userDetails) {
    List<EmailDeviceAndContentDetails> emailDeviceAndContentDetails = new ArrayList<>();

    userDetails.stream()
        .forEach(
            u -> {
              EmailDeviceAndContentDetails emailDeviceContent = new EmailDeviceAndContentDetails();
              emailDeviceContent.setEventId(UUID.randomUUID());
              emailDeviceContent.setUserName(u.getUserName());
              emailDeviceContent.setEmailAddress(u.getEmailAddress());
              emailDeviceContent.setDeviceTokens(u.getDeviceTokenList());
              emailDeviceContent.setEmailContent(emailRequestBody.getSubject());
              emailDeviceAndContentDetails.add(emailDeviceContent);
            });

    RequiredEmailContentEvent requiredEmailContentEvent = new RequiredEmailContentEvent();
    requiredEmailContentEvent.setEmailDeviceAndContentDetails(emailDeviceAndContentDetails);
    return publishNotification(requiredEmailContentEvent);
  }

  public boolean publishNotification(final RequiredEmailContentEvent requiredEmailContentEvent)
      throws RuntimeException {
    try {
      for (EmailDeviceAndContentDetails emailDeviceAndContentDetails :
          requiredEmailContentEvent.getEmailDeviceAndContentDetails()) {
        String msg = NotificationUtility.toJson(emailDeviceAndContentDetails);
        CompletableFuture<SendResult<String, String>> future =
            this.kafkaTemplate.send(TOPIC_EMAIL_NOTIFICATION, msg);
        future.whenComplete(
            (result, ex) -> {
              if (ex == null) {
                System.out.println(
                    "Sent message = ["
                        + msg
                        + "] with offset ["
                        + result.getRecordMetadata().offset()
                        + "]");
              } else {
                throw new RuntimeException(
                    "Unable to send message = [" + msg + "] due to : " + ex.getMessage());
              }
            });
        return true;
      }
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    return false;
  }
}
