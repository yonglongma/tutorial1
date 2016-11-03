package com.alibaba.tutorial1.modules.system.response;

import com.alibaba.framework.base.BaseResponse;

/**
 * Created by 马永龙 on 2016/10/2.
 */
public class SmsCreateResponse extends BaseResponse{

    private static final long serialVersionUID = 1L;

    /** 新创建的手机短信通知表ID */
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
