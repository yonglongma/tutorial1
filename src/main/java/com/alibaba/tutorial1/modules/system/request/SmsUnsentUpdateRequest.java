/**
 * @(#)SmsUnsentUpdateRequest.java
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
package com.alibaba.tutorial1.modules.system.request;

import com.alibaba.framework.base.BaseUpdateRequest;
import javax.validation.constraints.NotNull;


/**
 * Created by 马永龙 on 2016/10/3.
 */
public class SmsUnsentUpdateRequest extends BaseUpdateRequest {
    
    /**
     * 主键 
     */
    @NotNull(message = "主键不能为空")
    private  Long   id;
    
    /**
     * 发送次数 
     */
    
    private  Integer   sendCount;
    

    
    public Long getId() {
    return this.id;
    }

    public void setId(Long  id) {
    this.id = id;
    }
    
    public Integer getSendCount() {
    return this.sendCount;
    }

    public void setSendCount(Integer  sendCount) {
    this.sendCount = sendCount;
    }
    

}
