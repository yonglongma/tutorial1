/**
 * @(#)PassportUpdateRequest.java Copyright (c) 2014-2014  苏州犀牛网络科技有限公司 版权所有
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

import com.alibaba.framework.base.BaseUpdateRequest;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by 马永龙 on 2016/10/3.
 */
public class PassportUpdateRequest extends BaseUpdateRequest {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空")
    private Long id;

    /**
     * 用户ID
     */

    private Long userId;

    /**
     * 领用日期
     */

    private Date issueTime;

    /**
     * 过期时间
     */

    private Date expireTime;

    /**
     * 注销时间
     */

    private Date revokeTime;

    /**
     * 注销类型
     */

    @Length(min = 0, max = 50, message = "注销类型长度不合法")
    private String revokeType;

    /**
     * 领用IP
     */

    @Length(min = 0, max = 50, message = "领用IP长度不合法")
    private String issueIp;

    /**
     * 领用设备
     */

    private String issueClient;


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

    public Date getIssueTime() {
        return this.issueTime;
    }

    public void setIssueTime(Date issueTime) {
        this.issueTime = issueTime;
    }

    public Date getExpireTime() {
        return this.expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Date getRevokeTime() {
        return this.revokeTime;
    }

    public void setRevokeTime(Date revokeTime) {
        this.revokeTime = revokeTime;
    }

    public String getRevokeType() {
        return this.revokeType;
    }

    public void setRevokeType(String revokeType) {
        this.revokeType = revokeType;
    }

    public String getIssueIp() {
        return this.issueIp;
    }

    public void setIssueIp(String issueIp) {
        this.issueIp = issueIp;
    }

    public String getIssueClient() {
        return this.issueClient;
    }

    public void setIssueClient(String issueClient) {
        this.issueClient = issueClient;
    }


}
