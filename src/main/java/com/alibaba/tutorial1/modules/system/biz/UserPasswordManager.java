/**
 * @(#)UserPasswordManager.java Copyright (c) 2014-2014  苏州犀牛网络科技有限公司 版权所有
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

import com.alibaba.tutorial1.modules.system.domain.Passport;
import com.alibaba.tutorial1.modules.system.request.PasswordForgetRequest;
import com.alibaba.tutorial1.modules.system.request.UserPasswordCreateRequest;
import com.alibaba.tutorial1.modules.system.request.UserPasswordGetRequest;
import com.alibaba.tutorial1.modules.system.request.UserPasswordUpdateRequest;
import com.alibaba.tutorial1.modules.system.response.PasswordForgetResponse;
import com.alibaba.tutorial1.modules.system.response.UserPasswordCreateResponse;
import com.alibaba.tutorial1.modules.system.response.UserPasswordGetResponse;
import com.alibaba.tutorial1.modules.system.response.UserPasswordUpdateResponse;

/**
 * Created by 马永龙 on 2016/10/3.
 */
public interface UserPasswordManager {

    /**
     * 根据Id获取用户密码表
     *
     * @param request  获取用户密码表请求
     * @param passport 用户护照
     * @return 获取用户密码表应答
     */
    UserPasswordGetResponse get(UserPasswordGetRequest request, Passport passport);

    /**
     * 创建用户密码表
     *
     * @param request  创建用户密码表请求
     * @param passport 用户护照
     * @return 创建用户密码表应答
     */
    UserPasswordCreateResponse create(UserPasswordCreateRequest request, Passport passport);

    /**
     * 更新用户密码表
     *
     * @param request  更新用户密码表请求
     * @param passport 用户护照
     * @return 更新用户密码表应答
     */
    UserPasswordUpdateResponse update(UserPasswordUpdateRequest request, Passport passport);

    /**
     * 忘记密码
     * @param request
     * @return
     */
    PasswordForgetResponse forget(PasswordForgetRequest request, Passport passport);

}
