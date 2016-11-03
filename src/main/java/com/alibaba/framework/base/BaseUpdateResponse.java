package com.alibaba.framework.base;

/**
 * Created by 马永龙 on 2016/9/22.
 */
public class BaseUpdateResponse extends BaseResponse{
    private Long result;

    public BaseUpdateResponse() {
    }

    public Long getResult() {
        return this.result;
    }

    public void setResult(Long result) {
        this.result = result;
    }
}
