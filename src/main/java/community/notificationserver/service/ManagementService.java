package community.notificationserver.service;

import community.notificationserver.entity.DeviceDetails;
import community.notificationserver.entity.UserDetails;

public interface ManagementService {
    String registerUserWithDeviceDetails(DeviceDetails deviceDetails);
    Object getRegisteredDeviceAndUserDetails(String key);
}
