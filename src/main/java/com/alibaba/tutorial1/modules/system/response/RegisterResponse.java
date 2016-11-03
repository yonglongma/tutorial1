package com.alibaba.tutorial1.modules.system.response;

import com.alibaba.framework.base.BaseResponse;
import com.alibaba.tutorial1.modules.system.domain.Passport;
import com.alibaba.tutorial1.modules.system.domain.User;

/**
 * Created by 马永龙 on 2016/10/2.
 */
public class RegisterResponse extends BaseResponse {

    /**
     * 新用户的护照信息
     */
    private Passport passport;

    /**
     * 新用户的个人信息
     */
    private User user;

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
}
