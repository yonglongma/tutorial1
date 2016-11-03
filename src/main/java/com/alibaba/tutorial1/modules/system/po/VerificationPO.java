/**
 * @(#)VerificationPO.java Copyright (c) 2014-2014  苏州犀牛网络科技有限公司 版权所有
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
public class VerificationPO extends BasePO {


    /**
     * 主键,
     */
    private Long id;

    /**
     * 用户ID,
     */
    private Long userId;

    /**
     * 手机号码,
     */
    private String mobile;

    /**
     * 验证码,
     */
    private String code;

    /**
     * 生效时间,
     */
    private Date activeTime;

    /**
     * 失效时间,
     */
    private Date inactiveTime;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getActiveTime() {
        return this.activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    public Date getInactiveTime() {
        return this.inactiveTime;
    }

    public void setInactiveTime(Date inactiveTime) {
        this.inactiveTime = inactiveTime;
    }

}