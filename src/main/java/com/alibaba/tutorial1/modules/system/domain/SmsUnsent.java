/**
 * @(#)SmsUnsent.java Copyright (c) 2014-2014  苏州犀牛网络科技有限公司 版权所有
 * xiniunet. All rights reserved.
 * <p/>
 * This software is the confidential and proprietary
 * information of  xiniunet.
 * ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only
 * in accordance with the terms of the contract agreement
 * you entered into with xiniunet.
 */
package com.alibaba.tutorial1.modules.system.domain;

import com.alibaba.framework.base.BaseDomain;

/**
 * Created by 马永龙 on 2016/10/3.
 */
public class SmsUnsent extends BaseDomain {


    /**
     * 主键
     */
    private Long id;

    /**
     * 承租人ID
     */
    private Long tenantId;

    /**
     * 发送次数
     */
    private Integer sendCount;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getSendCount() {
        return this.sendCount;
    }

    public void setSendCount(Integer sendCount) {
        this.sendCount = sendCount;
    }

}