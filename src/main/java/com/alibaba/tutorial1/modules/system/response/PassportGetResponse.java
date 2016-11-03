package com.alibaba.tutorial1.modules.system.response;

import com.alibaba.framework.base.BaseResponse;
import com.alibaba.tutorial1.modules.system.domain.Passport;

/**
 * Created by 马永龙 on 2016/9/22.
 */
public class PassportGetResponse extends BaseResponse {

    /**
     * 用户护照信息
     */
    private Passport passport;

    public Passport getPassport() {
        return this.passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }
}
