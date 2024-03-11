package community.notificationserver.service.impl;

import community.notificationserver.entity.UserInfoEntity;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisCacheService {

  private final RedisTemplate<Integer, Object> redisTemplate;

  public RedisCacheService(RedisTemplate<Integer, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public void setValue(final int key, final Object object) {
    redisTemplate.opsForValue().set(key, object);
  }

  public void setValueWithTTL(final int key, final Object object, final long ttl) {
    redisTemplate.opsForValue().set(key, object, ttl);
  }

  public UserInfoEntity getValue(final int key) {
    return null;
    //redisTemplate.opsForValue().get(key);
  }

  public Object deleteKey(final int key) {
    return redisTemplate.opsForValue().getAndDelete(key);
  }

  public boolean doesExist(final int key) {
    return redisTemplate.opsForValue().get(key) != null;
  }
}
