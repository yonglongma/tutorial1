package com.alibaba.tutorial1.modules.system.request;


import com.alibaba.framework.base.BaseRequest;

import javax.validation.constraints.NotNull;

/**
 * Created by 马永龙 on 2016/10/3.
 */
public class VerificationCheckRequest extends BaseRequest {
    /**
     * 用户ID(可选)
     */
    private Long userId;
    /**
     * 手机号码
     */
    @NotNull(message = "手机号码不能为空")
    private String mobile;
    /**
     * 验证码
     */
    @NotNull(message = "验证码不能为空")
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
