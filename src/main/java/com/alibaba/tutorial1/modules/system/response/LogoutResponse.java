package com.alibaba.tutorial1.modules.system.response;

import com.alibaba.framework.base.BaseResponse;

/**
 * Created by 马永龙 on 2016/10/5.
 */
public class LogoutResponse extends BaseResponse {

    /**
     * 注销的结果
     */
    private Long result;

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }
}
