package com.alibaba.tutorial1.modules.system.biz;

import com.alibaba.tutorial1.modules.system.request.*;
import com.alibaba.tutorial1.modules.system.domain.Passport;
import com.alibaba.tutorial1.modules.system.response.*;

/**
 * Created by 马永龙 on 2016/9/12.
 */
public interface UserManager {

    /**
     * 创建用户表
     *
     * @param request  创建用户表请求
     * @param passport 用户护照
     * @return 创建用户表应答
     */
    UserCreateResponse create(UserCreateRequest request, Passport passport);

    /**
     * 高级查询用户表
     *
     * @param request  高级查询用户表请求
     * @param passport 用户护照
     * @return 高级查询用户表应答
     */
    UserFindResponse find(UserFindRequest request, Passport passport);

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
     * * @param request 注册参数
     * @return 新的用户信息
     */
    RegisterResponse register(RegisterRequest request);

    /**
     * 根据Id获取用户表
     *
     * @param request  获取用户表请求
     * @param passport 用户护照
     * @return 获取用户表应答
     */
    UserGetResponse get(UserGetRequest request, Passport passport);

}
