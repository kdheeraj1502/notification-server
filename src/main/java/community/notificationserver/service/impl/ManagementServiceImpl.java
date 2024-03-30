package community.notificationserver.service.impl;

import community.notificationserver.entity.DeviceDetails;
import community.notificationserver.entity.DeviceInfoEntity;
import community.notificationserver.repository.DeviceInfoRepository;
import community.notificationserver.mapper.DeviceMapper;
import community.notificationserver.service.ManagementService;
import community.notificationserver.utility.DeviceTokenGenerator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ManagementServiceImpl implements ManagementService {
    private final RedisCacheService redisCacheService;
    private final DeviceInfoRepository deviceInfoRepository;
    private final DeviceMapper deviceMapper;

    public ManagementServiceImpl(
            RedisCacheService redisCacheService,
            DeviceInfoRepository deviceInfoRepository,
            DeviceMapper deviceMapper
    ) {
        this.redisCacheService = redisCacheService;
        this.deviceInfoRepository = deviceInfoRepository;
        this.deviceMapper = deviceMapper;
    }

    @Override
    public String registerUserWithDeviceDetails(DeviceDetails deviceDetails) {
        try {
            String id = DeviceTokenGenerator.generateDeviceToken(8);
            deviceDetails.setDeviceId(id);
            redisCacheService.setValue(id, deviceDetails);
            redisCacheService.setValue(deviceDetails.getUserDetails().getUserId(), deviceDetails.getUserDetails());
            DeviceInfoEntity deviceInfoEntity = deviceMapper.dtoToEntity(deviceDetails);
            deviceInfoEntity.setLast_logged_in_at(LocalDateTime.now());
            deviceInfoRepository.save(deviceInfoEntity);
            return id;
        } catch (Exception ex) {
            return "Failed";
        }
    }

    @Override
    public DeviceDetails getRegisteredDeviceAndUserDetails(String key) {
        return (DeviceDetails) redisCacheService.getValue(key);
    }
}
