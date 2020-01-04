package team.orbitwheel.core.percentage;

import java.lang.annotation.*;
import java.math.BigDecimal;

/**
 * 百分比通过json序列化时候的显示格式
 * @author shiyajian
 * create: 2020-01-04
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PercentageFormat {

    /**
     * 精度，默认2位，可以修改
     */
    int scale() default 2;

    /**
     * 格式化显示时候的精度规则，数据库真实数据不变
     */
    int roundingModel() default BigDecimal.ROUND_HALF_UP;
}
