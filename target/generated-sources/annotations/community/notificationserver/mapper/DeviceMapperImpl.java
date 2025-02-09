package community.notificationserver.mapper;

import community.notificationserver.entity.DeviceDetails;
import community.notificationserver.entity.DeviceInfoEntity;
import community.notificationserver.entity.UserDetails;
import community.notificationserver.entity.UserInfoEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-21T22:12:58+0400",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class DeviceMapperImpl implements DeviceMapper {

    @Override
    public DeviceInfoEntity dtoToEntity(DeviceDetails deviceDetails) {
        if ( deviceDetails == null ) {
            return null;
        }

        DeviceInfoEntity deviceInfoEntity = new DeviceInfoEntity();

        deviceInfoEntity.setDeviceId( deviceDetails.getDeviceId() );
        deviceInfoEntity.setDeviceToken( deviceDetails.getDeviceToken() );
        deviceInfoEntity.setUserInfo( userDtoToEntity( deviceDetails.getUserDetails() ) );
        deviceInfoEntity.setLast_logged_in_at( deviceDetails.getLastLoggedInAt() );

        return deviceInfoEntity;
    }

    @Override
    public DeviceDetails entityToDto(DeviceInfoEntity deviceInfoEntity) {
        if ( deviceInfoEntity == null ) {
            return null;
        }

        DeviceDetails deviceDetails = new DeviceDetails();

        deviceDetails.setDeviceId( deviceInfoEntity.getDeviceId() );
        deviceDetails.setDeviceToken( deviceInfoEntity.getDeviceToken() );
        deviceDetails.setUserDetails( entityToUserDto( deviceInfoEntity.getUserInfo() ) );
        deviceDetails.setLastLoggedInAt( deviceInfoEntity.getLast_logged_in_at() );

        return deviceDetails;
    }

    @Override
    public UserInfoEntity userDtoToEntity(UserDetails userDetails) {
        if ( userDetails == null ) {
            return null;
        }

        UserInfoEntity userInfoEntity = new UserInfoEntity();

        userInfoEntity.setUserId( userDetails.getUserId() );
        userInfoEntity.setUserName( userDetails.getUserName() );
        userInfoEntity.setEmailAddress( userDetails.getEmailAddress() );
        userInfoEntity.setMobileNumber( userDetails.getMobileNumber() );
        userInfoEntity.setCountryCode( userDetails.getCountryCode() );
        userInfoEntity.setDeviceInfoEntityList( mapDeviceTokenList( userDetails.getDeviceTokenList() ) );

        return userInfoEntity;
    }

    @Override
    public UserDetails entityToUserDto(UserInfoEntity userInfoEntity) {
        if ( userInfoEntity == null ) {
            return null;
        }

        UserDetails userDetails = new UserDetails();

        userDetails.setUserId( userInfoEntity.getUserId() );
        userDetails.setUserName( userInfoEntity.getUserName() );
        userDetails.setEmailAddress( userInfoEntity.getEmailAddress() );
        userDetails.setMobileNumber( userInfoEntity.getMobileNumber() );
        userDetails.setCountryCode( userInfoEntity.getCountryCode() );
        userDetails.setDeviceTokenList( mapDeviceEntityListToStringList( userInfoEntity.getDeviceInfoEntityList() ) );

        return userDetails;
    }
}
