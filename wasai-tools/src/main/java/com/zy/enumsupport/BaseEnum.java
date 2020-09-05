package com.zy.enumsupport;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = EnumSerializer.class)
public interface BaseEnum {

    /**
     * 序列化时调用此方法
     *
     * @return
     */
    String getText();

}
