package community.notificationserver.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@RedisHash("user_details")
@Data
public class UserDetails implements Serializable {
    private static long serialId = -1772948778978090L;

    private String userId;
    private String userName;
    private String emailAddress;
    private String mobileNumber;
    private String countryCode;
    private List<String> deviceTokenList;

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

    public List<String> getDeviceTokenList() {
        return deviceTokenList;
    }

    public void setDeviceTokenList(List<String> deviceTokenList) {
        this.deviceTokenList = deviceTokenList;
    }
}

