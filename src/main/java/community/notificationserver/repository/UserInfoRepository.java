package community.notificationserver.repository;

import community.notificationserver.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface UserInfoRepository extends JpaRepository<UserInfoEntity, Integer> {
    Collection<UserInfoEntity> findAllByUserIdIn(List<String> userIds);
}
