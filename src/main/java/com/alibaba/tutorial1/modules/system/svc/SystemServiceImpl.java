package com.alibaba.tutorial1.modules.system.svc;

import com.alibaba.framework.util.ValidationUtil;
import com.alibaba.tutorial1.modules.system.biz.PassportManager;
import com.alibaba.tutorial1.modules.system.biz.UserManager;
import com.alibaba.tutorial1.modules.system.biz.VerificationManager;
import com.alibaba.tutorial1.modules.system.domain.Passport;
import com.alibaba.tutorial1.modules.system.request.*;
import com.alibaba.tutorial1.modules.system.response.*;
import com.alibaba.tutorial1.modules.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 马永龙 on 2016/10/3.
 */
public class SystemServiceImpl implements SystemService{

    @Autowired
    private UserManager userManager;
    @Autowired
    private VerificationManager verificationManager;
    @Autowired
    private PassportManager passportManager;

    /**
     * 登录
     *
     * @param request 登录参数
     * @return 登录的结果
     */
    @Override
    public LoginResponse login(LoginRequest request) {
        LoginResponse response = new LoginResponse();
        ValidationUtil.validate(request, response);
        if(response.hasError()) {
            return response;
        }
        response = userManager.login(request);
        return response;
    }

    /**
     * 注销
     *
     * @param request  注销请求
     * @param passport 护照信息
     */
    @Override
    public LogoutResponse logout(LogoutRequest request, Passport passport) {
        LogoutResponse response = new LogoutResponse();
        ValidationUtil.validate(request, response);
        if(response.hasError()) {
            return response;
        }
        response = userManager.logout(request, passport);
        return response;
    }

    /**
     * 注册
     *
     * @param request 注册参数
     * @return 新的用户信息
     */
    @Override
    public RegisterResponse register(RegisterRequest request) {
        RegisterResponse response = new RegisterResponse();
        ValidationUtil.validate(request, response);
        if(response.hasError()) {
            return response;
        }
        response = userManager.register(request);
        return response;
    }

    /**
     * 创建验证码
     *
     * @param request  创建验证码请求
     * @return 创建验证码应答
     */
    @Override
    public VerificationCreateResponse createVerification(VerificationCreateRequest request) {
        VerificationCreateResponse response = new VerificationCreateResponse();
        ValidationUtil.validate(request, response);
        if(response.hasError()) {
            return response;
        }
        response = verificationManager.create(request);
        return response;
    }

    /**
     * 检验验证码
     *
     * @param request  检验验证码请求
     * @return 检验验证码应答
     */
    @Override
    public VerificationCheckResponse checkVerification(VerificationCheckRequest request) {
        VerificationCheckResponse response = new VerificationCheckResponse();
        ValidationUtil.validate(request, response);
        if(response.hasError()) {
            return response;
        }
        response = verificationManager.check(request);
        return response;
    }

    /**
     * 根据Id获取用户护照
     *
     * @param request 获取用户护照请求
     * @return 获取用户护照应答
     */
    @Override
    public PassportGetResponse getPassport(PassportGetRequest request) {
        PassportGetResponse response = new PassportGetResponse();
        ValidationUtil.validate(request, response);
        if(response.hasError()) {
            return response;
        }
        response = passportManager.get(request);
        return response;
    }

    /**
     * 根据Id获取用户表
     *
     * @param request  获取用户表请求
     * @param passport 用户护照
     * @return 获取用户表应答
     */
    @Override
    public UserGetResponse getUser(UserGetRequest request, Passport passport) {
        UserGetResponse response = new UserGetResponse();
        ValidationUtil.validate(request, response);
        if(response.hasError()) {
            return response;
        }
        response = userManager.get(request,passport);
        return response;
    }
}
