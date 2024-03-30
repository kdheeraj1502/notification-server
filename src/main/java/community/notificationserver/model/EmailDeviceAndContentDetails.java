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

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public List<String> getDeviceTokens() {
        return deviceTokens;
    }

    public void setDeviceTokens(List<String> deviceTokens) {
        this.deviceTokens = deviceTokens;
    }

    public List<EmailRequestBody.Content> getEmailContent() {
        return emailContent;
    }

    public void setEmailContent(List<EmailRequestBody.Content> emailContent) {
        this.emailContent = emailContent;
    }
}
