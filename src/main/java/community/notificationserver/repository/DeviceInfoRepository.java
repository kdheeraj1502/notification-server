package community.notificationserver.repository;

import community.notificationserver.entity.DeviceInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface DeviceInfoRepository extends JpaRepository<DeviceInfoEntity, String> {

    Collection<DeviceInfoEntity> findAllByUserInfoUserIdIn(List<String> userIds);
}
