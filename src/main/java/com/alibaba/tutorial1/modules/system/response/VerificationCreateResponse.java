/**
 * @(#)VerificationCreateResponse.java Copyright (c) 2014-2014  苏州犀牛网络科技有限公司 版权所有
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

/**
 * Created by 马永龙 on 2016/10/3.
 */
public class VerificationCreateResponse extends BaseResponse {
    private static final long serialVersionUID = 1L;
    /**
     * 新创建的验证码
     */
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
