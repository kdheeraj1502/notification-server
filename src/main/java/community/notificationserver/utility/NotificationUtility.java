package community.notificationserver.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class NotificationUtility<T> {
  final ObjectMapper objectMapper = new ObjectMapper();

  public static <T> String toJson(final T object) throws JsonProcessingException {
    return objectMapper.writeValueAsString(object);
  }

  public static <T> T parseJson(final String message, final Class<T> clazz)
      throws JsonProcessingException {
    return objectMapper.readValue(message, clazz);
  }
}
