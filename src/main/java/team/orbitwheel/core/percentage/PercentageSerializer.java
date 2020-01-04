package team.orbitwheel.core.percentage;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

import java.io.IOException;

/**
 * 常规的百分比序列化
 * @author shiyajian
 * create: 2020-01-04
 */
public class PercentageSerializer extends JsonSerializer<Percentage> implements ContextualSerializer {

    public static final PercentageSerializer INSTANCE = new PercentageSerializer();

    private PercentageFormatter formatter;

    public PercentageSerializer() {
        super();
        this.formatter = new PercentageFormatter();
    }

    public PercentageSerializer(PercentageFormatter formatter) {
        super();
        this.formatter = formatter;
    }

    @Override
    public void serialize(Percentage percentage, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (percentage != null && percentage.getValue() != null) {
            jsonGenerator.writeNumber(this.formatter.format(percentage));
        }
    }

    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) {
        PercentageFormat annotation = property.getAnnotation(PercentageFormat.class);
        if (annotation != null) {
            return new PercentageSerializer(new PercentageFormatter(annotation.scale(), annotation.roundingModel()));
        }
        return new PercentageSerializer();
    }
}
