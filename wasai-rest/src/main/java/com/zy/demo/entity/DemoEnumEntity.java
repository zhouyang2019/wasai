package com.zy.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zy.enumsupport.BaseIntCodeEnum;
import com.zy.enumsupport.BaseStrCodeEnum;
import com.zy.mybatis.IntCodeEnumTypeHandler;
import com.zy.mybatis.StrCodeEnumTypeHandler;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName(value = "demo_enum", autoResultMap = true)
public class DemoEnumEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    @TableField(typeHandler = IntCodeEnumTypeHandler.class)
    private IntTypeEnum intType;

    @TableField(typeHandler = StrCodeEnumTypeHandler.class)
    private StrTypeEnum strType;

    public enum IntTypeEnum implements BaseIntCodeEnum {
        ACTIVE(1, "激活"),
        INACTIVE(0, "未激活"),
        CLOSED(2, "关闭"),
        ;

        private int code;
        private String text;

        IntTypeEnum(int code, String text) {
            this.code = code;
            this.text = text;
        }

        @Override
        public int getCode() {
            return this.code;
        }

        @Override
        public String getText() {
            return this.text;
        }
    }

    public enum StrTypeEnum implements BaseStrCodeEnum {
        ACTIVE("ACTIVE", "激活"),
        INACTIVE("INACTIVE", "未激活"),
        CLOSED("CLOSED", "关闭"),
        ;

        private String code;
        private String text;

        StrTypeEnum(String code, String text) {
            this.code = code;
            this.text = text;
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public String getText() {
            return this.text;
        }
    }

}
