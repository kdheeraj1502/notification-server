package community.notificationserver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import community.notificationserver.model.EmailDeviceAndContentDetails;
import community.notificationserver.model.RequiredEmailContentEvent;
import community.notificationserver.service.impl.EmailService;
import community.notificationserver.utility.NotificationUtility;
import jakarta.mail.MessagingException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static community.notificationserver.constant.NotificationConstants.GROUP_ID;
import static community.notificationserver.constant.NotificationConstants.TOPIC_EMAIL_NOTIFICATION;

@Service
public class WorkerService {
    private final EmailService emailService;
    private static final String EMAIL_SUBJECT = "!!Offers Offers Offers!!";

    public WorkerService(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = TOPIC_EMAIL_NOTIFICATION, groupId = GROUP_ID)
    public void listenGroupEmailNotification(String message) throws JsonProcessingException, MessagingException {
        RequiredEmailContentEvent emailContent =
                NotificationUtility.parseJson(message, RequiredEmailContentEvent.class);
        for(EmailDeviceAndContentDetails details : emailContent.getEmailDeviceAndContentDetails()) {
            emailService.sendMail(details.getEmailAddress(), details.getUserName(), EMAIL_SUBJECT);
        }
    }
}
