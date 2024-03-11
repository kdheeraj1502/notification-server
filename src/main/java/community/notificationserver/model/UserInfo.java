package community.notificationserver.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash("user_info")
public class UserInfo implements Serializable {
  private static long serialId = -17729479827897298L;

  @Id
  private int user_id;
  private String userName;
  private String emailAddress;
  private String mobileNumber;
  private String countryCode;
  private LocalDateTime createdAt;
  private List<DeviceInfo> deviceInfoEntityList;
}
