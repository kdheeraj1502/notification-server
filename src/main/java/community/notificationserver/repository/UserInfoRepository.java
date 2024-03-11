package community.notificationserver.repository;

import community.notificationserver.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfoEntity, Integer> {}
