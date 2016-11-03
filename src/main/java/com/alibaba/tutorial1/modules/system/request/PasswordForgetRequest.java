package com.alibaba.tutorial1.modules.system.request;

import com.alibaba.framework.base.BaseRequest;
import javax.validation.constraints.NotNull;

/**
 * Created by 马永龙 on 2016/10/3.
 */
public class PasswordForgetRequest extends BaseRequest {
    /**
     * 手机号码
     */
    @NotNull(message = "手机号码不能为空")
    private String mobile;
    /**
     * 密码
     */
    @NotNull(message = "新的密码不能为空")
    private String password;
    /**
     * 短信验证码
     */
    @NotNull(message = "短信验证码不能为空")
    private String code;
    /**
     * 密码长度
     */
    private Integer passwordLength;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getPasswordLength() {
        return passwordLength;
    }

    public void setPasswordLength(Integer passwordLength) {
        this.passwordLength = passwordLength;
    }
}
