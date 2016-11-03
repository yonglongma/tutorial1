package com.alibaba.framework.exception;

/**
 * Created by 马永龙 on 2016/9/10.
 */
public enum ErrorType {
    UNIQUENESS_ERROR,
    EXPECTATION_NULL,
    BUSINESS_ERROR,
    SYSTEM_ERROR,
    INVALID_PARAMETER,
    OTHER,
    STACK_DUMP;

    private ErrorType() {
    }
}
