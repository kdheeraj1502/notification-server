package community.notificationserver.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(schema = "PUSH_NOTIFICATION_SERVICE", name = "device_info")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DeviceInfoEntity implements Serializable {
  private static long serialId = -15979827897298L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String device_token;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id", referencedColumnName = "user_id")
  private UserInfoEntity userInfo;

  private LocalDateTime last_logged_in_at;
}
