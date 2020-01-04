package team.orbitwheel.core.percentage;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 百分比 mybatis 的 typeHandler
 * @author shiyajian
 * create: 2020-01-04
 */
@MappedJdbcTypes({JdbcType.DECIMAL})
@MappedTypes({Percentage.class})
public class PercentageTypeHandler implements TypeHandler<Percentage> {

    public void setParameter(PreparedStatement preparedStatement, int i, Percentage percentage, JdbcType jdbcType) throws SQLException {
        PercentageProcess annotation = percentage.getClass().getAnnotation(PercentageProcess.class);
        PercentageProcessor processor = annotation != null ? new PercentageProcessor(annotation.scale(), annotation.roundingModel()) : new PercentageProcessor();
        preparedStatement.setBigDecimal(i, processor.process(percentage));
    }

    public Percentage getResult(ResultSet resultSet, String s) throws SQLException {
        return new Percentage(resultSet.getBigDecimal(s));
    }

    public Percentage getResult(ResultSet resultSet, int i) throws SQLException {
        return new Percentage(resultSet.getBigDecimal(i));
    }

    public Percentage getResult(CallableStatement callableStatement, int i) throws SQLException {
        return new Percentage(callableStatement.getBigDecimal(i));
    }
}
