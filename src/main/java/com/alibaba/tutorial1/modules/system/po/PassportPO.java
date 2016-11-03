package com.alibaba.tutorial1.modules.system.po;

import com.alibaba.framework.base.BasePO;
import com.alibaba.tutorial1.modules.system.enumication.IdentityTypeEnum;
import java.util.Date;

/**
 * Created by 马永龙 on 2016/9/10.
 */
public class PassportPO extends BasePO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户身份
     */
    private IdentityTypeEnum identityType;

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
    private String revokeType;

    /**
     * 领用IP
     */
    private String issueIp;

    /**
     * 领用设备
     */
    private String issueClient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public IdentityTypeEnum getIdentityType() {
        return identityType;
    }

    public void setIdentityType(IdentityTypeEnum identityType) {
        this.identityType = identityType;
    }

    public Date getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(Date issueTime) {
        this.issueTime = issueTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Date getRevokeTime() {
        return revokeTime;
    }

    public void setRevokeTime(Date revokeTime) {
        this.revokeTime = revokeTime;
    }

    public String getRevokeType() {
        return revokeType;
    }

    public void setRevokeType(String revokeType) {
        this.revokeType = revokeType;
    }

    public String getIssueIp() {
        return issueIp;
    }

    public void setIssueIp(String issueIp) {
        this.issueIp = issueIp;
    }

    public String getIssueClient() {
        return issueClient;
    }

    public void setIssueClient(String issueClient) {
        this.issueClient = issueClient;
    }
}
