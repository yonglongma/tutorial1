package com.alibaba.tutorial1.modules.system.response;

import com.alibaba.framework.base.BaseResponse;
import com.alibaba.tutorial1.modules.system.domain.Passport;
import com.alibaba.tutorial1.modules.system.domain.User;

/**
 * Created by 马永龙 on 2016/10/2.
 */
public class LoginResponse extends BaseResponse{

    /**
     * 登录后的护照信息
     */
    private Passport passport;

    /**
     * 用户的信息
     */
    private User user;

    /**
     * 是否需要修改登录密码
     */
    private Boolean needChangeLogin;

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getNeedChangeLogin() {
        return needChangeLogin;
    }

    public void setNeedChangeLogin(Boolean needChangeLogin) {
        this.needChangeLogin = needChangeLogin;
    }
}
