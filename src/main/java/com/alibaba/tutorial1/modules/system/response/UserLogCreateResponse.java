package com.alibaba.tutorial1.modules.system.response;

import com.alibaba.framework.base.BaseResponse;
import com.alibaba.tutorial1.modules.system.domain.UserLog;

/**
 * Created by 马永龙 on 2016/9/10.
 */
public class UserLogCreateResponse extends BaseResponse {

    private UserLog userLog;

    public UserLog getUserLog() {
        return userLog;
    }

    public void setUserLog(UserLog userLog) {
        this.userLog = userLog;
    }
}
