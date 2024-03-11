package community.notificationserver.controller;

import community.notificationserver.model.EmailRequestBody;
import community.notificationserver.service.EmailNotificationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private final EmailNotificationService emailNotificationService;

    public NotificationController(final EmailNotificationService emailNotificationService) {
        this.emailNotificationService = emailNotificationService;
    }

    @PostMapping("/email/msg")
    public void publishEmail(@RequestBody final EmailRequestBody emailRequestBody) {
        emailNotificationService.publishNotification(emailRequestBody);
    }
}
