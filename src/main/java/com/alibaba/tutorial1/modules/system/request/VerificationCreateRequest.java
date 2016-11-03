/**
 * @(#)VerificationCreateRequest.java Copyright (c) 2014-2014  苏州犀牛网络科技有限公司 版权所有
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
 * Created by 马永龙 on 2016/10/3.
 */
public class VerificationCreateRequest extends BaseRequest {

    /**
     * 用户ID
     */

    private Long userId;

    /**
     * 手机号码
     */

    @Length(min = 0, max = 20, message = "手机号码长度不合法")
    private String mobile;


    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
