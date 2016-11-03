/**
 * @(#)SmsManager.java
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
package com.alibaba.tutorial1.modules.system.biz;

import com.alibaba.tutorial1.modules.system.domain.Passport;
import com.alibaba.tutorial1.modules.system.request.SmsCreateRequest;
import com.alibaba.tutorial1.modules.system.request.SmsSendRequest;
import com.alibaba.tutorial1.modules.system.response.SmsCreateResponse;
import com.alibaba.tutorial1.modules.system.response.SmsSendResponse;

/**
 * Created by 马永龙 on 2016/10/3.
 */
public interface SmsManager {

    /**
     * 创建手机短信通知表
     *
     * @param request 创建手机短信通知表请求
     * @param passport 用户护照
     * @return 创建手机短信通知表应答
     */
    SmsCreateResponse create(SmsCreateRequest request, Passport passport);

    /**
     * 发送短信
     *
     * @param request 发送短信请求
     * @param passport 用户护照
     * @return 发送短信应答
     */
    SmsSendResponse send(SmsSendRequest request, Passport passport);


}
