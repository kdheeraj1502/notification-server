package community.notificationserver.model;

import lombok.Data;

import java.util.List;

@Data
public class RequiredEmailContentEvent {
    private List<EmailDeviceAndContentDetails> emailDeviceAndContentDetails;

    public List<EmailDeviceAndContentDetails> getEmailDeviceAndContentDetails() {
        return emailDeviceAndContentDetails;
    }

    public void setEmailDeviceAndContentDetails(List<EmailDeviceAndContentDetails> emailDeviceAndContentDetails) {
        this.emailDeviceAndContentDetails = emailDeviceAndContentDetails;
    }
}
