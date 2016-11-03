/**
 * @(#)UserPasswordGetResponse.java Copyright (c) 2014-2014  苏州犀牛网络科技有限公司 版权所有
 * xiniunet. All rights reserved.
 * <p/>
 * This software is the confidential and proprietary
 * information of  xiniunet.
 * ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only
 * in accordance with the terms of the contract agreement
 * you entered into with xiniunet.
 */
package com.alibaba.tutorial1.modules.system.response;

import com.alibaba.framework.base.BaseResponse;
import com.alibaba.tutorial1.modules.system.domain.UserPassword;

/**
 * Created by 马永龙 on 2016/10/3.
 */
public class UserPasswordGetResponse extends BaseResponse {

    /**
     * 用户密码表信息
     */
    private UserPassword userPassword;

    public UserPassword getUserPassword() {
        return this.userPassword;
    }

    public void setUserPassword(UserPassword userPassword) {
        this.userPassword = userPassword;
    }
}
