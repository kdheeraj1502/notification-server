package community.notificationserver.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import community.notificationserver.entity.DeviceInfoEntity;
import community.notificationserver.entity.UserInfoEntity;
import community.notificationserver.model.EmailDeviceAndContentDetails;
import community.notificationserver.model.RequiredEmailContentEvent;
import community.notificationserver.repository.DeviceInfoRepository;
import community.notificationserver.model.EmailRequestBody;
import community.notificationserver.repository.UserInfoRepository;
import community.notificationserver.service.EmailNotificationService;
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

  public EmailNotificationServiceImpl(
          KafkaTemplate<String, String> kafkaTemplate,
          RedisCacheService redisCacheService,
          DeviceInfoRepository deviceInfoRepository, UserInfoRepository userInfoRepository) {
    this.kafkaTemplate = kafkaTemplate;
    this.redisCacheService = redisCacheService;
    this.deviceInfoRepository = deviceInfoRepository;
    this.userInfoRepository = userInfoRepository;
  }

  @Override
  public boolean publishNotification(EmailRequestBody emailRequestBody) {
    System.out.println(userInfoRepository.findAll());
    System.out.println(deviceInfoRepository.findAll());
    return true;
  }

 // @Override
  @Transactional
  public boolean publishNotification1(EmailRequestBody emailRequestBody) {
    List<Integer> recipientUserIds =
        emailRequestBody.getTo().stream().map(EmailRequestBody.Recipient::getUserId).toList();
    List<UserInfoEntity> userInfoEntities = new ArrayList<>();
    List<Integer> unAvailableUserIds = new ArrayList<>();
    for (int userId : recipientUserIds) {
      UserInfoEntity userInfo = redisCacheService.getValue(userId);
      if (userInfo == null) {
        unAvailableUserIds.add(userId);
      } else {
        userInfoEntities.add(userInfo);
      }
    }
    if (!unAvailableUserIds.isEmpty()) {
      Collection<DeviceInfoEntity> deviceInfoEntities =
          deviceInfoRepository.findAllByUserInfoUserIdIn(unAvailableUserIds);
      for (DeviceInfoEntity deviceInfoEntity : deviceInfoEntities) {
        userInfoEntities.add(deviceInfoEntity.getUserInfo());
      }
    }

    List<EmailDeviceAndContentDetails> emailDeviceAndContentDetails = new ArrayList<>();
    userInfoEntities.stream()
        .forEach(
            u -> {
              EmailDeviceAndContentDetails emailDeviceContent = new EmailDeviceAndContentDetails();
              emailDeviceContent.setEventId(UUID.randomUUID());
              emailDeviceContent.setUserName(u.getUserName());
              emailDeviceContent.setEmailAddress(u.getEmailAddress());
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
