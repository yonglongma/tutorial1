/**
 * @(#)UserPasswordManagerImpl.java Copyright (c) 2014-2014  苏州犀牛网络科技有限公司 版权所有
 * xiniunet. All rights reserved.
 * <p/>
 * This software is the confidential and proprietary
 * information of  xiniunet.
 * ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only
 * in accordance with the terms of the contract agreement
 * you entered into with xiniunet.
 */
package com.alibaba.tutorial1.modules.system.biz;

import com.alibaba.framework.base.BaseManagerImpl;
import com.alibaba.framework.exception.ErrorType;
import com.alibaba.tutorial1.modules.system.dal.UserMapper;
import com.alibaba.tutorial1.modules.system.dal.UserPasswordMapper;
import com.alibaba.tutorial1.modules.system.domain.Passport;
import com.alibaba.tutorial1.modules.system.domain.User;
import com.alibaba.tutorial1.modules.system.domain.UserPassword;
import com.alibaba.tutorial1.modules.system.po.UserPasswordPO;
import com.alibaba.tutorial1.modules.system.request.*;
import com.alibaba.tutorial1.modules.system.response.*;
import com.alibaba.tutorial1.tool.EncryptUtil;
import com.alibaba.tutorial1.tool.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * Created by 马永龙 on 2016/10/3.
 */
@Transactional
@Service("UserPasswordManager")
public class UserPasswordManagerImpl extends BaseManagerImpl implements UserPasswordManager {

    @Autowired
    private UserPasswordMapper userPasswordMapper;
    @Autowired
    private VerificationManager verificationManager;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PassportManager passportManager;

    /**
     * 根据Id获取用户密码表
     *
     * @param request  获取用户密码表请求
     * @param passport 用户护照
     * @return 获取用户密码表应答
     */
    @Override
    @Transactional(readOnly = true)
    public UserPasswordGetResponse get(UserPasswordGetRequest request, Passport passport) {
        UserPasswordPO entity = userPasswordMapper.getById(request.getId(), passport);
        UserPasswordGetResponse response = new UserPasswordGetResponse();
        if(entity != null) {
            UserPassword userPassword = this.getMapper().map(entity, UserPassword.class);
            response.setUserPassword(userPassword);
        } else {
            response.addError(ErrorType.EXPECTATION_NULL, Message.COMMON_GET_FAILURE);
        }
        return response;
    }

    /**
     * 创建用户密码表
     *
     * @param request  创建用户密码表请求
     * @param passport 用户护照
     * @return 创建用户密码表应答
     */
    @Override
    public UserPasswordCreateResponse create(UserPasswordCreateRequest request, Passport passport) {
        UserPasswordCreateResponse response = new UserPasswordCreateResponse();

        UserPasswordPO entity = this.getMapper().map(request, UserPasswordPO.class);
        if(request.getId() == null) {
            response.addError(ErrorType.INVALID_PARAMETER, "个人ID不能为空");
            return response;
        }

        //生成新的盐
        String salt = UUID.randomUUID().toString().toUpperCase();
        entity.setLoginSalt(salt);
        entity.setNeedChangeLogin(false);
        entity.setLoginPassword(EncryptUtil.SHA1(request.getLoginPassword().toUpperCase() + salt).toUpperCase());

        if(1 == userPasswordMapper.insert(entity, passport)) {
            response.setId(request.getId());
        } else {
            response.addError(ErrorType.EXPECTATION_NULL, Message.COMMON_CREATE_FAILURE);
        }
        return response;
    }


    /**
     * 更新用户密码表
     *
     * @param request  更新用户密码表请求
     * @param passport 用户护照
     * @return 更新用户密码表应答
     */
    @Override
    public UserPasswordUpdateResponse update(UserPasswordUpdateRequest request, Passport passport) {
        UserPasswordPO entity = this.getMapper().map(request, UserPasswordPO.class);

        UserPasswordUpdateResponse response = new UserPasswordUpdateResponse();
        Long result = userPasswordMapper.update(entity, passport);
        if(result != 1) {
            response.addError(ErrorType.BUSINESS_ERROR, Message.COMMON_UPDATE_FAILURE);
            return response;
        }
        response.setResult(result);
        return response;
    }

    /**
     * 忘记密码
     *
     * @param request  忘记密码请求
     * @param passport 用户护照
     * @return 忘记密码应答
     */
    @Override
    public PasswordForgetResponse forget(PasswordForgetRequest request,Passport passport) {
        PasswordForgetResponse response = new PasswordForgetResponse();
        // 验证验证码
        VerificationCheckRequest checkRequest = new VerificationCheckRequest();
        checkRequest.setMobile(request.getMobile());
        checkRequest.setCode(request.getCode());
        VerificationCheckResponse checkResponse = verificationManager.check(checkRequest);
        if(checkResponse.hasError()) {
            response.addErrors(checkResponse.getErrors());
            return response;
        }

        // 查询用户信息
        UserFindRequest findRequest = new UserFindRequest();
        findRequest.setMobile(request.getMobile());
        List<User> userPOList = userMapper.find(findRequest, passport);
        if(userPOList.isEmpty()) {
            response.addError("mobile", "不存在的帐号");
            return response;
        }

        User user = userPOList.get(0);
        if(passport == null) {
            passport = new Passport();
            passport.setUserId(user.getId());
        }

        // 更新密码
        String salt = UUID.randomUUID().toString().toUpperCase();
        UserPasswordPO passwordPO = userPasswordMapper.getById(user.getId(), passport);
        passwordPO.setLoginSalt(salt);
        passwordPO.setLoginPassword(EncryptUtil.SHA1(request.getPassword().toUpperCase() + salt).toUpperCase());
        userPasswordMapper.update(passwordPO, passport);

        //注销所有未过期护照（除当前使用的护照外）
        //passportManager.invokeAll(passport);
        return response;
    }

}
