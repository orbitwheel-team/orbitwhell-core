package team.orbitwheel.core.percentage;

import java.lang.annotation.*;
import java.math.BigDecimal;

/**
 * 百分比存储时候的注解，可能需要缩小精度
 * @author shiyajian
 * create: 2020-01-04
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PercentageProcess {

    /**
     * 精度，默认2位，可以修改
     */
    int scale() default 2;

    /**
     * 处理数据的方式，部分场景下，可能需要向下取整
     */
    int roundingModel() default BigDecimal.ROUND_HALF_UP;
}
