package community.notificationserver.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(schema = "PUSH_NOTIFICATION_SERVICE", name = "user_info")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserInfoEntity implements Serializable {
  private static long serialId = -17729479827897298L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id", nullable = false)
  private int userId;
  private String userName;
  private String emailAddress;
  private String mobileNumber;
  private String countryCode;
  private LocalDateTime createdAt;
  @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL)
  private List<DeviceInfoEntity> deviceInfoEntityList;
}
