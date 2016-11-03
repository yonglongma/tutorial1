/**
 * @(#)SmsPO.java Copyright (c) 2014-2014  苏州犀牛网络科技有限公司 版权所有
 * xiniunet. All rights reserved.
 * <p>
 * This software is the confidential and proprietary
 * information of  xiniunet.
 * ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only
 * in accordance with the terms of the contract agreement
 * you entered into with xiniunet.
 */
package com.alibaba.tutorial1.modules.system.po;

import com.alibaba.framework.base.BasePO;
import java.util.Date;

/**
 * Created by 马永龙 on 2016/10/3.
 */
public class SmsPO extends BasePO {


    /**
     * 主键,
     */
    private Long id;

    /**
     * 业务类型,
     */
    private String businessType;

    /**
     * 业务ID,
     */
    private Long businessId;

    /**
     * 业务类别,
     */
    private String businessCategory;

    /**
     * 手机号码,
     */
    private String mobilePhone;

    /**
     * 消息文本,
     */
    private String messageText;

    /**
     * 消息时间,
     */
    private Date messageTime;

    /**
     * 发送时间,
     */
    private Date sendTime;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessType() {
        return this.businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public Long getBusinessId() {
        return this.businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public String getBusinessCategory() {
        return this.businessCategory;
    }

    public void setBusinessCategory(String businessCategory) {
        this.businessCategory = businessCategory;
    }

    public String getMobilePhone() {
        return this.mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getMessageText() {
        return this.messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Date getMessageTime() {
        return this.messageTime;
    }

    public void setMessageTime(Date messageTime) {
        this.messageTime = messageTime;
    }

    public Date getSendTime() {
        return this.sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

}