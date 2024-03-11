package community.notificationserver.model;

import lombok.Data;

import java.util.List;

@Data
public class RequiredEmailContentEvent {
    private List<EmailDeviceAndContentDetails> emailDeviceAndContentDetails;
}
