package com.zy.mybatis;

import com.zy.enumsupport.BaseStrCodeEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 参考 EnumOrdinalTypeHandler
 */
public class StrCodeEnumTypeHandler<T extends Enum<T> & BaseStrCodeEnum> extends BaseTypeHandler<T> {

    private final Class<T> type;

    public StrCodeEnumTypeHandler(Class<T> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String code = rs.getString(columnName);
        return rs.wasNull() ? null : toBaseStrCodeEnum(this.type, code);
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String code = rs.getString(columnIndex);
        return rs.wasNull() ? null : toBaseStrCodeEnum(this.type, code);
    }

    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String code = cs.getString(columnIndex);
        return cs.wasNull() ? null : toBaseStrCodeEnum(this.type, code);
    }

    private <T extends Enum<T> & BaseStrCodeEnum> T toBaseStrCodeEnum(Class<T> enumClass, String code) {
        T[] enumConstants = enumClass.getEnumConstants();
        for (T t : enumConstants) {
            if (code != null && code.equals(t.getCode())) {
                return t;
            }
        }
        return null;
    }
}
