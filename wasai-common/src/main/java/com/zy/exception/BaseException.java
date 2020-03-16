package com.zy.exception;

import java.util.ArrayList;
import java.util.List;

public class BaseException extends RuntimeException {

    private String code = ErrorConstants.BASE_EXCEPTION;

    private List<String> params = new ArrayList<>();

    protected BaseException() {
        super();
    }

    protected BaseException(String msg) {
        super(msg);
    }

    protected BaseException(String code, String msg) {
        super(msg);
        this.code = code;
    }

    protected BaseException(String code, String msg, List<String> params) {
        super(msg);
        this.code = code;
        this.params = params;
    }

    public String getCode() {
        return this.code;
    }

    public String[] getParams() {
        return this.params.toArray(new String[0]);
    }

}
