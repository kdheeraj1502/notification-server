package community.notificationserver.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "device_info")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class DeviceInfoEntity implements Serializable {
  private static long serialId = -15979827897298L;

  @Id
  //@GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "device_id")
  private String deviceId;

  @JoinColumn(name = "device_token")
  private String deviceToken;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id", referencedColumnName = "user_id")
  private UserInfoEntity userInfo;

  @Column(name = "last_logged_in_at")
  private LocalDateTime last_logged_in_at;


  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  public String getDeviceToken() {
    return deviceToken;
  }

  public void setDeviceToken(String deviceToken) {
    this.deviceToken = deviceToken;
  }

  public UserInfoEntity getUserInfo() {
    return userInfo;
  }

  public void setUserInfo(UserInfoEntity userInfo) {
    this.userInfo = userInfo;
  }

  public LocalDateTime getLast_logged_in_at() {
    return last_logged_in_at;
  }

  public void setLast_logged_in_at(LocalDateTime last_logged_in_at) {
    this.last_logged_in_at = last_logged_in_at;
  }
}
