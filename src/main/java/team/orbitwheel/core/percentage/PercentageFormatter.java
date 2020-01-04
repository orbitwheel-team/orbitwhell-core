package team.orbitwheel.core.percentage;

import java.math.BigDecimal;

/**
 * @author shiyajian
 * create: 2020-01-04
 */
public class PercentageFormatter {

    private static final int DEFAULT_SCALE = 2;

    public static final int DEFAULT_ROUNDING_MODEL = BigDecimal.ROUND_HALF_UP;

    private Integer scale;

    private Integer roundingModel;

    public PercentageFormatter() {
        this.scale = DEFAULT_SCALE;
        this.roundingModel = DEFAULT_ROUNDING_MODEL;
    }

    public PercentageFormatter(Integer scale, Integer roundingModel) {
        this.scale = ((scale == null) ? DEFAULT_SCALE : scale);
        this.roundingModel = ((roundingModel == null) ? DEFAULT_ROUNDING_MODEL : roundingModel);
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public Integer getRoundingModel() {
        return roundingModel;
    }

    public void setRoundingModel(Integer roundingModel) {
        this.roundingModel = roundingModel;
    }

    public BigDecimal format(Percentage percentage) {
        if (percentage == null || percentage.getValue() == null) {
            return null;
        }
        return percentage.getValue().setScale(this.scale, this.roundingModel);
    }
}
