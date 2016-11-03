package com.alibaba.tutorial1.modules.system.request;

import com.alibaba.framework.base.BaseFindRequest;

/**
 * Created by 马永龙 on 2016/10/3.
 */
public class VerificationFindRequest extends BaseFindRequest {

    /**
     * 用户ID,
     */
    private Long userId;


    /**
     * 手机号码,
     */
    private String mobile;


    /**
     * 验证码,
     */
    private String code;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
