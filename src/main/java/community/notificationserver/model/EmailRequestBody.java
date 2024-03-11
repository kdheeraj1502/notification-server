package community.notificationserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class EmailRequestBody {
  @JsonProperty("to")
  private List<Recipient> to;

  @JsonProperty("from")
  private Sender from;

  @JsonProperty("content")
  private List<Content> subject;

  @Data
  public static class Recipient {
    @JsonProperty("user_id")
    private int userId;
  }

  @Data
  public static class Sender {
    @JsonProperty("email")
    private String email;
  }

  @Data
  public static class Content {
    @JsonProperty("type")
    private String type;

    @JsonProperty("content")
    private String value;
  }
}
