/**
 * @(#)SmsUnsentManager.java
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
import com.alibaba.tutorial1.modules.system.request.SmsUnsentCreateRequest;
import com.alibaba.tutorial1.modules.system.request.SmsUnsentDeleteRequest;
import com.alibaba.tutorial1.modules.system.request.SmsUnsentUpdateRequest;
import com.alibaba.tutorial1.modules.system.response.SmsUnsentCreateResponse;
import com.alibaba.tutorial1.modules.system.response.SmsUnsentDeleteResponse;
import com.alibaba.tutorial1.modules.system.response.SmsUnsentUpdateResponse;

/**
 * Created by 马永龙 on 2016/10/3.
 */
public interface SmsUnsentManager {

    /**
     * 创建手机消息通知表(未发送的)
     *
     * @param request 创建手机消息通知表(未发送的)请求
     * @param passport 用户护照
     * @return 创建手机消息通知表(未发送的)应答
     */
    SmsUnsentCreateResponse create(SmsUnsentCreateRequest request, Passport passport);


    /**
     * 更新手机消息通知表(未发送的)
     *
     * @param request 更新手机消息通知表(未发送的)请求
     * @param passport 用户护照
     * @return 更新手机消息通知表(未发送的)应答
     */
    SmsUnsentUpdateResponse update(SmsUnsentUpdateRequest request, Passport passport);


    /**
     * 删除手机消息通知表(未发送的)
     *
     * @param request 删除手机消息通知表(未发送的)请求
     * @param passport 用户护照
     * @return 删除手机消息通知表(未发送的)应答
     */
    SmsUnsentDeleteResponse delete(SmsUnsentDeleteRequest request, Passport passport);


}
