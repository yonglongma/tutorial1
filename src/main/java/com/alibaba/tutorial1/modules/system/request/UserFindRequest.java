package com.alibaba.tutorial1.modules.system.request;

import com.alibaba.framework.base.BaseFindRequest;

/**
 * Created by 马永龙 on 2016/9/12.
 */
public class UserFindRequest extends BaseFindRequest {

    /**
     * 昵称,
     */
    private String nick;

    /**
     * 手机号,
     */
    private String mobile;

    /**
     * 邮箱,
     */
    private String mail;

    /**
     * 身份类型,
     */
    private String identityType;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }
}
