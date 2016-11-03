package com.alibaba.framework.base;

import com.alibaba.framework.exception.Error;
import com.alibaba.framework.exception.ErrorType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 马永龙 on 2016/9/10.
 */
public class BaseResponse implements Serializable{
    private static final long serialVersionUID = 1L;
    private List<com.alibaba.framework.exception.Error> errors;
    private List<Error> _getErrors() {
        if(this.errors == null) {
            this.errors = new ArrayList();
        }

        return this.errors;
    }

    public BaseResponse(){

    }
    public void addError(String code, String message) {
        this._getErrors().add(new Error(code, message));
    }

    public void addError(ErrorType type, String message) {
        this._getErrors().add(new Error(type, message));
    }

    public void addError(Error error) {
        this._getErrors().add(error);
    }

    public void addErrors(List<Error> errors) {
        Iterator var2 = errors.iterator();

        while(var2.hasNext()) {
            Error error = (Error)var2.next();
            this.addError(error);
        }

    }

    public List<Error> getErrors() {
        return new ArrayList(this._getErrors());
    }

    public boolean hasError() {
        return this.errors != null && !this.errors.isEmpty();
    }
}
