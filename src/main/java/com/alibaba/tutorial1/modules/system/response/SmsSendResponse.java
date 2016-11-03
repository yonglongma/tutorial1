/**
 * @(#)SmsCreateResponse.java
 *
 * Copyright (c) 2014-2014  苏州犀牛网络科技有限公司 版权所有
 * xiniunet. All rights reserved.
 *
 * This software is the confidential and proprietary
 * information of  xiniunet.
 * ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only
 * in accordance with the terms of the contract agreement
 * you entered into with xiniunet.
 */
package com.alibaba.tutorial1.modules.system.response;

import com.alibaba.framework.base.BaseResponse;

/**
 * Created by 马永龙 on 2016/10/3.
 */
public class SmsSendResponse extends BaseResponse {
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
