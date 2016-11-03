package com.alibaba.tutorial1.modules.system.service;

import com.alibaba.tutorial1.modules.system.domain.Passport;
import com.alibaba.tutorial1.modules.system.request.*;
import com.alibaba.tutorial1.modules.system.response.*;

/**
 * Created by 马永龙 on 2016/10/3.
 */
public interface SystemService {

    /**
     * 登录
     *
     * @param request 登录参数
     * @return 登录的结果
     */
    LoginResponse login(LoginRequest request);

    /**
     * 注销
     *
     * @param request  注销请求
     * @param passport 护照信息
     */
    LogoutResponse logout(LogoutRequest request, Passport passport);

    /**
     * 注册
     *
     * @param request 注册参数
     * @return 新的用户信息
     */
    RegisterResponse register(RegisterRequest request);

    /**
     * 创建验证码
     *
     * @param request  创建验证码请求
     * @return 创建验证码应答
     */
    VerificationCreateResponse createVerification(VerificationCreateRequest request);


    /**
     * 检验验证码
     *
     * @param request  检验验证码请求
     * @return 检验验证码应答
     */
    VerificationCheckResponse checkVerification(VerificationCheckRequest request);

    /**
     * 根据Id获取用户护照
     *
     * @param request 获取用户护照请求
     * @return 获取用户护照应答
     */
    PassportGetResponse getPassport(PassportGetRequest request);

    /**
     * 根据Id获取用户表
     *
     * @param request  获取用户表请求
     * @param passport 用户护照
     * @return 获取用户表应答
     */
    UserGetResponse getUser(UserGetRequest request, Passport passport);
}
