package community.notificationserver.service.impl;

import community.notificationserver.entity.DeviceDetails;
import community.notificationserver.entity.UserInfoEntity;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisCacheService {

  private final RedisTemplate<String, Object> redisTemplate;

  public RedisCacheService(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public void setValue(final String key, final Object object) {
    redisTemplate.opsForValue().set(key, object);
  }

  public void setValueWithTTL(final String key, final Object object, final long ttl) {
    redisTemplate.opsForValue().set(key, object, ttl);
  }

  public Object getValue(final String key) {
    return redisTemplate.opsForValue().get(key);
  }

  public Object deleteKey(final String key) {
    return redisTemplate.opsForValue().getAndDelete(key);
  }

  public boolean doesExist(final String key) {
    return redisTemplate.opsForValue().get(key) != null;
  }
}
