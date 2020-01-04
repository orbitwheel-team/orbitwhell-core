package team.orbitwheel.core.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import team.orbitwheel.core.percentage.Percentage;
import team.orbitwheel.core.percentage.PercentageDeserializer;
import team.orbitwheel.core.percentage.PercentageSerializer;

/**
 * JSON 标准规范
 * @author shiyajian
 * create: 2020-01-04
 */
public final class JsonSpecification {

    private JsonSpecification() { /* no instance */ }

    private static ObjectMapper objectMapper;

    static {
        ObjectMapper objectMapper = new ObjectMapper();
        //对象的所有字段全部列入
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //关闭日期序列化为时间戳的功能
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        //忽略空Bean转json的错误
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //所有的日期格式都统一为以下的样式，即yyyy-MM-dd HH:mm:ss
        // objectMapper.setDateFormat();
        //忽略 在json字符串中存在，但是在java对象中不存在对应属性的情况。防止错误
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        SimpleModule percentageModule = new SimpleModule();
        percentageModule.addSerializer(Percentage.class, PercentageSerializer.INSTANCE);
        percentageModule.addDeserializer(Percentage.class, PercentageDeserializer.INSTANCE);
        objectMapper.registerModule(percentageModule);
    }

    /**
     * 获得标准的 Jackson 的 ObjectMapper，所有项目采用这个统一配置
     * @return 标准的 Jackson Object Mapper
     */
    public static ObjectMapper getStandardObjectMapper() {
        return objectMapper;
    }

}
