package com.zy.support;


import com.zy.exception.ErrorConstants;

import java.io.Serializable;

public class ApiReturn<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code = "ok";
    private String message = "success";
    private T data;

    public ApiReturn() {
        super();
    }

    public ApiReturn(T data) {
        super();
        this.data = data;
    }

    public ApiReturn buildFail(String message) {
        return buildFail(ErrorConstants.UNKNOWN_ERROR, message);
    }

    public ApiReturn buildFail(String code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
