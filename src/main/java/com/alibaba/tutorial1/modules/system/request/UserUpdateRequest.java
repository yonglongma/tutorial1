/**
 * @(#)UserUpdateRequest.java Copyright (c) 2014-2014  苏州犀牛网络科技有限公司 版权所有
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
import com.alibaba.tutorial1.modules.system.enumication.IdentityTypeEnum;
import javax.validation.constraints.NotNull;

/**
 * Created by 马永龙 on 2016/9/12.
 */
public class UserUpdateRequest extends BaseUpdateRequest {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空")
    private Long id;

    /**
     * 昵称
     */
    private String name;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 是否绑定手机
     */
    private Boolean mobileBind;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 是否绑定邮箱
     */
    private Boolean mailBind;

    /**
     * 身份类型
     */
    private IdentityTypeEnum identityType;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 个性签名
     */
    private String sign;

    /**
     * 微信号
     */
    private String wechatId;

    /**
     * 支付宝账号
     */
    private String alipayId;

    /**
     * 微博号
     */
    private String weiboId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Boolean getMobileBind() {
        return mobileBind;
    }

    public void setMobileBind(Boolean mobileBind) {
        this.mobileBind = mobileBind;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Boolean getMailBind() {
        return mailBind;
    }

    public void setMailBind(Boolean mailBind) {
        this.mailBind = mailBind;
    }

    public IdentityTypeEnum getIdentityType() {
        return identityType;
    }

    public void setIdentityType(IdentityTypeEnum identityType) {
        this.identityType = identityType;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public String getAlipayId() {
        return alipayId;
    }

    public void setAlipayId(String alipayId) {
        this.alipayId = alipayId;
    }

    public String getWeiboId() {
        return weiboId;
    }

    public void setWeiboId(String weiboId) {
        this.weiboId = weiboId;
    }
}
