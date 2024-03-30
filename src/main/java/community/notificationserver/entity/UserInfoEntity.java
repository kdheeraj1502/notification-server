package community.notificationserver.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user_info")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class UserInfoEntity implements Serializable {
  private static long serialId = -17729479827111111L;

  @Id
 // @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id", nullable = false)
  private String userId;

  @Column(name = "user_name")
  private String userName;
  @Column(name = "email_address")
  private String emailAddress;
  @Column(name = "mobile_number")
  private String mobileNumber;
  @Column(name = "country_code")
  private String countryCode;
  @Column(name = "created_at")
  private LocalDateTime createdAt;
  @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL)
  private List<DeviceInfoEntity> deviceInfoEntityList;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
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

  public String getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public List<DeviceInfoEntity> getDeviceInfoEntityList() {
    return deviceInfoEntityList;
  }

  public void setDeviceInfoEntityList(List<DeviceInfoEntity> deviceInfoEntityList) {
    this.deviceInfoEntityList = deviceInfoEntityList;
  }
}
