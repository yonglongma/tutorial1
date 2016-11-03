/**
 * @(#)VerificationManager.java Copyright (c) 2014-2014  苏州犀牛网络科技有限公司 版权所有
 * xiniunet. All rights reserved.
 * <p/>
 * This software is the confidential and proprietary
 * information of  xiniunet.
 * ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only
 * in accordance with the terms of the contract agreement
 * you entered into with xiniunet.
 */
package com.alibaba.tutorial1.modules.system.biz;

import com.alibaba.tutorial1.modules.system.request.VerificationCheckRequest;
import com.alibaba.tutorial1.modules.system.request.VerificationCreateRequest;
import com.alibaba.tutorial1.modules.system.response.VerificationCheckResponse;
import com.alibaba.tutorial1.modules.system.response.VerificationCreateResponse;

/**
 * Created by 马永龙 on 2016/10/3.
 */
public interface VerificationManager {

    /**
     * 创建验证码
     *
     * @param request  创建验证码请求
     * @return 创建验证码应答
     */
    VerificationCreateResponse create(VerificationCreateRequest request);


    /**
     * 检验验证码
     *
     * @param request  检验验证码请求
     * @return 检验验证码应答
     */
    VerificationCheckResponse check(VerificationCheckRequest request);
}
