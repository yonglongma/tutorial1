/**
 * @(#)UserPasswordCreateRequest.java Copyright (c) 2014-2014  苏州犀牛网络科技有限公司 版权所有
 * xiniunet. All rights reserved.
 * <p/>
 * This software is the confidential and proprietary
 * information of  xiniunet.
 * ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only
 * in accordance with the terms of the contract agreement
 * you entered into with xiniunet.
 */
package com.alibaba.tutorial1.modules.system.request;

import com.alibaba.framework.base.BaseRequest;
import org.hibernate.validator.constraints.Length;

/**
 /**
 * Created by 马永龙 on 2016/10/3.
 */
public class UserPasswordCreateRequest extends BaseRequest {
    /**
     * 用户ID(先创建用户后创建密码)
     */
    private Long id;

    /**
     * 登录密码(原始密码MD5得到)
     */
    @Length(min = 0, max = 50, message = "登录密码长度不合法")
    private String loginPassword;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginPassword() {
        return this.loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
}
