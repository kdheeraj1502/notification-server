package community.notificationserver.model;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class EmailDeviceAndContentDetails {
    private UUID eventId;
    private String userName;
    private String emailAddress;
    private List<String> deviceTokens;
    private List<EmailRequestBody.Content> emailContent;
}
