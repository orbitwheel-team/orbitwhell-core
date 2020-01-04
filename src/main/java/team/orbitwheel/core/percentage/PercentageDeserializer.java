package team.orbitwheel.core.percentage;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * 常规的百分比反序列化
 * @author shiyajian
 * create: 2020-01-04
 */
public class PercentageDeserializer extends JsonDeserializer<Percentage> {

    public static final PercentageDeserializer INSTANCE = new PercentageDeserializer();

    @Override
    public Percentage deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String value = jsonParser.getValueAsString();
        Percentage percentage = new Percentage();
        percentage.setValue(value);
        return percentage;
    }
}
