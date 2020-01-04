package team.orbitwheel.core.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * Json 工具类
 * @author shiyajian
 * create: 2020-01-04
 */
public final class JsonUtils {

    private JsonUtils() { /* no instance */ }

    /**
     * 对象转成字符串
     */
    public static String toJsonString(Object object) {
        ObjectMapper standardObjectMapper = JsonSpecification.getStandardObjectMapper();
        try {
            return standardObjectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将字符串转成对象
     */
    public static <T> T parse(String json, Class<T> clazz) {
        ObjectMapper standardObjectMapper = JsonSpecification.getStandardObjectMapper();
        try {
            return standardObjectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> parseArray(String json, Class<T> clazz) {
        ObjectMapper standardObjectMapper = JsonSpecification.getStandardObjectMapper();
        try {
            JavaType javaType = standardObjectMapper.getTypeFactory().constructParametricType(List.class, clazz);
            return standardObjectMapper.readValue(json, javaType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
