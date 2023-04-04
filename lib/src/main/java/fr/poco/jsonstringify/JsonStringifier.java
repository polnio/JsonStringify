package fr.poco.jsonstringify;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class JsonStringifier {
  public String stringify(Object object) throws Exception {
    if (Objects.isNull(object)) {
      return "null";
    }
    if (object instanceof String) {
      return "\"" + object + "\"";
    }
    if (object instanceof Number) {
      return object.toString();
    }
    if (object instanceof Iterable) {
      String tt = "[";
      for (Object o : (Iterable<?>) object) {
        tt += this.stringify(o);
      }
      tt += "]";
      return tt;
    }
    Class<?> clazz = object.getClass();
    if (!this.isStringifiable(object)) {
      throw new Exception(clazz.getName() + " is not stringifiable");
    }
    Map<String, String> elements = new HashMap<>();
    for (Field field : clazz.getDeclaredFields()) {
      field.setAccessible(true);
      if (field.isAnnotationPresent(JsonField.class)) {
        elements.put(this.getKey(field), this.stringify(field.get(object)));
      }
    }
    String jsonString = elements.entrySet()
        .stream()
        .map(entry -> "\"" + entry.getKey() + "\":" + entry.getValue())
        .collect(Collectors.joining(","));
    return "{" + jsonString + "}";
  }

  private boolean isStringifiable(Object object) {
    Class<?> clazz = object.getClass();
    if (!clazz.isAnnotationPresent(JsonStringifiable.class)) {
      return false;
    }
    return true;
  }

  private String getKey(Field field) {
    String value = field.getAnnotation(JsonField.class).key();
    return value.isEmpty() ? field.getName() : value;
  }
}
