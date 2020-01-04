package team.orbitwheel.core.percentage;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.ObjectUtils;
import team.orbitwheel.core.json.JsonUtils;

import java.math.BigDecimal;

/**
 * 百分比，0到100
 * @author shiyajian
 * create: 2020-01-04
 */
public class Percentage {

    /**
     * 百分比
     */
    private BigDecimal value;

    public static final BigDecimal DEFAULT_VALUE = BigDecimal.ZERO;

    private static final BigDecimal MAX_VALUE = new BigDecimal("100");

    public Percentage() {
        this.value = null;
    }

    public Percentage(Long value) {
        this.setValue(value);
    }

    public Percentage(Integer value) {
        this.setValue(value);
    }

    public Percentage(String value) {
        this.setValue(value);
    }

    public Percentage(BigDecimal value) {
        this.setValue(value);
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(String value) {
        if (value == null || value.trim().length() == 0) {
            this.value = null;
        } else {
            this.value = new BigDecimal(value);
        }
        checkRange(this.value);
    }

    public void setValue(Long value) {
        if (value == null) {
            this.value = null;
        } else {
            this.value = new BigDecimal(value);
        }
        checkRange(this.value);
    }

    public void setValue(Integer value) {
        if (value == null) {
            this.value = null;
        } else {
            this.value = new BigDecimal(value);
        }
        checkRange(this.value);
    }

    public void setValue(BigDecimal value) {
        this.value = value;
        checkRange(this.value);
    }

    private void checkRange(BigDecimal value) {
        if (value == null) {
            throw new IllegalArgumentException(" percentage can't be null ");
        }
        if (value.compareTo(DEFAULT_VALUE) < 0) {
            throw new IllegalArgumentException(" percentage must gt 0 ");
        }
        if (value.compareTo(MAX_VALUE) > 0) {
            throw new IllegalArgumentException(" percentage must lt 100 ");
        }
    }

    private BigDecimal multiply(BigDecimal multiplicand) {
        if (this.value == null) {
            throw new IllegalArgumentException(" percentage is null ");
        }
        return this.value.multiply(multiplicand);
    }

    private Boolean isLessThan(Percentage target) {
        return ObjectUtils.compare(this.value, target.getValue()) < 0;
    }

    private Boolean isLessThanOrEqual(Percentage target) {
        return ObjectUtils.compare(this.value, target.getValue()) <= 0;
    }

    private Boolean isGreaterThan(Percentage target) {
        return !isLessThanOrEqual(target);
    }

    private Boolean isGreaterThanOrEqual(Percentage target) {
        return !isLessThan(target);
    }

    /**
     * 求当前比率到100的差值
     */
    private Percentage negation() {
        if (this.value == null) {
            return new Percentage(MAX_VALUE);
        }
        return new Percentage(MAX_VALUE.subtract(this.value));
    }

    public static class Test {

        private String world;

        @PercentageFormat(scale = 4)
        private Percentage hello;

        public Percentage getHello() {
            return hello;
        }

        public void setHello(Percentage hello) {
            this.hello = hello;
        }
    }

    public static void main(String[] args) throws JsonProcessingException {
        Test test = new Test();
        test.setHello(new Percentage("90"));
        String s = JsonUtils.toJsonString(test);
        System.out.println(s);
        JsonUtils.parse(s, Test.class);
    }
}
