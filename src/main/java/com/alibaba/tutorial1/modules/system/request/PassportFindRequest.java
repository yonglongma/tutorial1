package com.alibaba.tutorial1.modules.system.request;

import com.alibaba.framework.base.BaseFindRequest;

/**
 * Created by 马永龙 on 2016/9/22.
 */
public class PassportFindRequest extends BaseFindRequest {

    /**
     * 用户ID
     */
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
