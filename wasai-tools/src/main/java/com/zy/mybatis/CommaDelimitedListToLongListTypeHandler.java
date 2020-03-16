package com.zy.mybatis;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.util.StringUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class CommaDelimitedListToLongListTypeHandler implements TypeHandler<List<Long>> {

    @Override
    public void setParameter(PreparedStatement ps, int i, List<Long> parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null) {
            ps.setString(i, StringUtils.collectionToCommaDelimitedString(parameter));
        }
    }

    @Override
    public List<Long> getResult(ResultSet rs, String columnName) throws SQLException {
        String columnValueStr = rs.getString(columnName);
        if (columnValueStr != null) {
            return StringUtils.commaDelimitedListToSet(columnValueStr)
                    .stream()
                    .map(e -> Long.valueOf(e)).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<Long> getResult(ResultSet rs, int columnIndex) throws SQLException {
        String columnValueStr = rs.getString(columnIndex);
        if (columnValueStr != null) {
            return StringUtils.commaDelimitedListToSet(columnValueStr)
                    .stream()
                    .map(e -> Long.valueOf(e)).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<Long> getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String columnValueStr = cs.getString(columnIndex);
        if (columnValueStr != null) {
            return StringUtils.commaDelimitedListToSet(columnValueStr)
                    .stream()
                    .map(e -> Long.valueOf(e)).collect(Collectors.toList());
        }
        return null;
    }

}
