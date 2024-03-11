package community.notificationserver.service;

import community.notificationserver.model.EmailRequestBody;

public interface EmailNotificationService {
    boolean publishNotification(final EmailRequestBody emailRequestBody);
}
