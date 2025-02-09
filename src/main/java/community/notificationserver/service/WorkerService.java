/*
package community.notificationserver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import community.notificationserver.model.EmailDeviceAndContentDetails;
import community.notificationserver.model.RequiredEmailContentEvent;
import community.notificationserver.service.impl.EmailService;
import community.notificationserver.utility.NotificationUtility;
import jakarta.mail.MessagingException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import static community.notificationserver.constant.NotificationConstants.GROUP_ID;
import static community.notificationserver.constant.NotificationConstants.TOPIC_EMAIL_NOTIFICATION;

@Service
public class WorkerService {
    private static final Logger logger = LoggerFactory.getLogger(WorkerService.class);
    private final EmailService emailService;
    private static final String EMAIL_SUBJECT = "!!Offers Offers Offers!!";

    public WorkerService(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = TOPIC_EMAIL_NOTIFICATION, groupId = GROUP_ID)
    public void listenGroupEmailNotification(String message) throws JsonProcessingException, MessagingException {
        try {
            EmailDeviceAndContentDetails emailContent =
                    NotificationUtility.parseJson(message, EmailDeviceAndContentDetails.class);
          //  emailService.sendMail(emailContent.getEmailAddress(), emailContent.getUserName(), EMAIL_SUBJECT);

            logger.info("Successfully processed email notification for message: {}", message);
         //   acknowledgment.acknowledge(); // Manually acknowledge the message
        } catch (JsonProcessingException e) { //| MessagingException e) {
            logger.error("Failed to process email notification for message: {}", message, e);
        }
    }
}
*/
