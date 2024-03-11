package community.notificationserver.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash("device_info")
public class DeviceInfo implements Serializable {
  private static long serialId = -15979827897298L;

  @Id
  private int id;

  private String device_token;

  private UserInfo userInfo;

  private LocalDateTime last_logged_in_at;
}
