package community.notificationserver.mapper;

import community.notificationserver.entity.DeviceDetails;
import community.notificationserver.entity.DeviceInfoEntity;
import community.notificationserver.entity.UserDetails;
import community.notificationserver.entity.UserInfoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.Optional.empty;
import static java.util.stream.Collectors.toList;

@Mapper(componentModel = "spring")
public interface DeviceMapper {
    @Mappings({
            @Mapping(source = "deviceId", target = "deviceId"),
            @Mapping(source = "deviceToken", target = "deviceToken"),
            @Mapping(source = "userDetails", target = "userInfo"),
            @Mapping(source = "lastLoggedInAt", target = "last_logged_in_at")
    })
    DeviceInfoEntity dtoToEntity(DeviceDetails deviceDetails);

    @Mappings({
            @Mapping(source = "deviceId", target = "deviceId"),
            @Mapping(source = "deviceToken", target = "deviceToken"),
            @Mapping(source = "userInfo", target = "userDetails"),
            @Mapping(source = "last_logged_in_at", target = "lastLoggedInAt")
    })
    DeviceDetails entityToDto(DeviceInfoEntity deviceInfoEntity);

    @Mappings({
            @Mapping(source = "userId", target = "userId"),
            @Mapping(source = "userName", target = "userName"),
            @Mapping(source = "emailAddress", target = "emailAddress"),
            @Mapping(source = "mobileNumber", target = "mobileNumber"),
            @Mapping(source = "countryCode", target = "countryCode"),
            @Mapping(source = "deviceTokenList", target = "deviceInfoEntityList")
    })
    UserInfoEntity userDtoToEntity(UserDetails userDetails);

    @Mappings({
            @Mapping(source = "userId", target = "userId"),
            @Mapping(source = "userName", target = "userName"),
            @Mapping(source = "emailAddress", target = "emailAddress"),
            @Mapping(source = "mobileNumber", target = "mobileNumber"),
            @Mapping(source = "countryCode", target = "countryCode"),
            @Mapping(source = "deviceInfoEntityList", target = "deviceTokenList")
    })
    UserDetails entityToUserDto(UserInfoEntity userInfoEntity);

    default List<DeviceInfoEntity> mapDeviceTokenList(List<String> deviceTokenList) {
        return Optional.ofNullable(deviceTokenList)
                .map(list -> list.stream()
                        .map(token -> {
                            DeviceInfoEntity deviceInfoEntity = new DeviceInfoEntity();
                            deviceInfoEntity.setDeviceToken(token);
                            return deviceInfoEntity;
                        })
                        .collect(Collectors.toList()))
                .orElse(emptyList());
    }

    default List<String> mapDeviceEntityListToStringList(List<DeviceInfoEntity> deviceInfoEntityList) {
        return Optional.of(deviceInfoEntityList)
                .map(list -> list.stream()
                        .map(DeviceInfoEntity::getDeviceToken)
                        .collect(toList()))
                .orElse(emptyList());
    }
}