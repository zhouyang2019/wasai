package com.zy.mybatis;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JsonObjectTypeHandler<T> extends BaseTypeHandler<T> {

    private Class<T> type;

    public JsonObjectTypeHandler(Class<T> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        try {
            ps.setString(i, ReaderWriter.write(parameter));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String _val = rs.getString(columnName);
        if (_val != null) {
            try {
                return ReaderWriter.read(_val, type);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        return null;
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String _val = rs.getString(columnIndex);
        if (_val != null) {
            try {
                return ReaderWriter.read(_val, type);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        return null;
    }

    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String _val = cs.getString(columnIndex);
        if (_val != null) {
            try {
                return ReaderWriter.read(_val, type);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        return null;
    }
}
