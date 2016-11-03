package com.alibaba.tutorial1.modules.system.response;

import com.alibaba.framework.base.BaseResponse;

/**
 * Created by 马永龙 on 2016/9/10.
 */
public class UserCreateResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;
    /**
     * 新创建的用户表ID
     */
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
