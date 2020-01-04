package team.orbitwheel.core.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import team.orbitwheel.core.json.JsonUtils;
import team.orbitwheel.core.percentage.Percentage;
import team.orbitwheel.core.percentage.PercentageFormat;

import java.math.BigDecimal;

/**
 * 比率，可正可负，不限大小
 * @author shiyajian
 * create: 2020-01-04
 */
public class Rate {

    private BigDecimal value;
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
