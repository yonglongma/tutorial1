package com.alibaba.tutorial1.modules.system.request;

import com.alibaba.framework.base.BaseRequest;
import com.alibaba.tutorial1.tool.Message;

import javax.validation.constraints.NotNull;

/**
 * Created by 马永龙 on 2016/10/2.
 */
public class LoginRequest extends BaseRequest {

    /**
     * 手机号或学号@学校编号
     */
    @NotNull(message = Message.LOGIN_REQUEST_ACCOUNT_NOT_NULL)
    private String account;

    /**
     * 登录密码
     */
    @NotNull(message = Message.LOGIN_REQUEST_PASSWORD_NOT_NULL)
    private String password;

    /**
     * 登录设备类型
     */
    private String clientType;

    /**
     * 设备IP地址
     */
    private String clientIp;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }
}
