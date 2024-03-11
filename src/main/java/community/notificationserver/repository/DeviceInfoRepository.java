package community.notificationserver.repository;

import community.notificationserver.entity.DeviceInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface DeviceInfoRepository extends JpaRepository<DeviceInfoEntity, Integer> {

    Collection<DeviceInfoEntity> findAllByUserInfoUserIdIn(List<Integer> userIds);
}
