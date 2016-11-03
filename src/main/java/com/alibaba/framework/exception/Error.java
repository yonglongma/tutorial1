package com.alibaba.framework.exception;

import java.io.Serializable;

/**
 * Created by 马永龙 on 2016/9/10.
 */
public class Error implements Serializable{
    private static final long serialVersionUID = 3L;
    private String code;
    private ErrorType type;
    private String message;

    public Error() {
    }

    public Error(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Error(ErrorType type, String message) {
        this.type = type;
        this.code = type.toString();
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorType getType() {
        return this.type;
    }

    public void setType(ErrorType type) {
        this.type = type;
    }
}
