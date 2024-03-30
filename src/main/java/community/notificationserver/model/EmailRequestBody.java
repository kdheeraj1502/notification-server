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

  public List<Recipient> getTo() {
    return to;
  }

  public void setTo(List<Recipient> to) {
    this.to = to;
  }

  public Sender getFrom() {
    return from;
  }

  public void setFrom(Sender from) {
    this.from = from;
  }

  public List<Content> getSubject() {
    return subject;
  }

  public void setSubject(List<Content> subject) {
    this.subject = subject;
  }

  @Data
  public static class Recipient {
    @JsonProperty("user_id")
    private String userId;

    public String getUserId() {
      return userId;
    }

    public void setUserId(String userId) {
      this.userId = userId;
    }
  }

  @Data
  public static class Sender {
    @JsonProperty("email")
    private String email;

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }
  }

  @Data
  public static class Content {
    @JsonProperty("type")
    private String type;

    @JsonProperty("content")
    private String value;

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public String getValue() {
      return value;
    }

    public void setValue(String value) {
      this.value = value;
    }
  }
}
