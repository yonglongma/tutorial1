package com.alibaba.tutorial1.web.home.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.framework.annotation.ApiName;
import com.alibaba.framework.util.SpringContext;
import com.alibaba.tutorial1.modules.system.domain.Passport;
import com.alibaba.tutorial1.modules.system.domain.User;
import com.alibaba.tutorial1.modules.system.request.LoginRequest;
import com.alibaba.tutorial1.modules.system.request.LogoutRequest;
import com.alibaba.tutorial1.modules.system.request.RegisterRequest;
import com.alibaba.tutorial1.modules.system.request.VerificationCheckRequest;
import com.alibaba.tutorial1.modules.system.response.LoginResponse;
import com.alibaba.tutorial1.modules.system.response.LogoutResponse;
import com.alibaba.tutorial1.modules.system.response.RegisterResponse;
import com.alibaba.tutorial1.modules.system.response.VerificationCheckResponse;
import com.alibaba.tutorial1.modules.system.service.SystemService;
import com.alibaba.tutorial1.tool.BaseUtil;
import com.alibaba.tutorial1.tool.CookieUtil;
import com.alibaba.tutorial1.tool.UrlUtil;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 马永龙 on 2016/10/3.
 */
public class System {

    private static SystemService systemService = SpringContext.getApplicationContext().getBean(SystemService.class);

    /**
     * 用户自主注册
     */
    @ApiName("/system/register.api")
    public static RegisterResponse register(JSONObject jsonObject, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        RegisterResponse response = new RegisterResponse();
        RegisterRequest request = JSON.toJavaObject(jsonObject, RegisterRequest.class);

        if (request.getName() == null) {
            response.addError("name", "请输入您的姓名");
            return response;
        }
        if (!request.getMobile().matches("^1[34578][0-9]{9}$")) {
            response.addError("mobile", "您的手机号格式不正确");
            return response;
        }
        if (request.getCode() == null) {
            response.addError("code", "请输入短信验证码");
            return response;
        }
        request.setIp(servletRequest.getRemoteAddr());

        //检验短信验证码是否正确
        VerificationCheckRequest checkRequest = new VerificationCheckRequest();
        checkRequest.setCode(request.getCode());
        checkRequest.setMobile(request.getMobile());
        VerificationCheckResponse checkResponse = systemService.checkVerification(checkRequest);
        if (checkResponse.hasError()) {
            response.addErrors(checkResponse.getErrors());
            return response;
        }

        response = systemService.register(request);
        if (response.hasError()) {
            return response;
        }
        User user = response.getUser();
        Passport passport = response.getPassport();

        //创建浏览器cookie用于保存passport信息，保存一周
        String deployMode = BaseUtil.getDeployMode();
        Cookie cookie = new Cookie(CookieUtil.getPassportCookieName(deployMode), passport.getId() + "");
        cookie.setPath("/");
        cookie.setMaxAge(7 * 24 * 3600);
        cookie.setDomain(UrlUtil.getSiteDomain(servletRequest));
        servletResponse.addCookie(cookie);

        return response;
    }

    /**
     * 登录
     */
    @ApiName("/system/login.api")
    public static LoginResponse login(JSONObject jsonObject,HttpServletRequest request,HttpServletResponse response){
        LoginRequest loginRequest = JSON.toJavaObject(jsonObject,LoginRequest.class);

        //登录
        loginRequest.setClientIp(request.getRemoteAddr());
        loginRequest.setClientType(request.getHeader("User-Agent").toLowerCase());

        LoginResponse loginResponse = systemService.login(loginRequest);
        if(loginResponse.hasError()) {
            return loginResponse;
        }

        //创建cookie用于保存passport信息，保存一周
        Passport passport = loginResponse.getPassport();
        String deployMode = BaseUtil.getDeployMode();
        Cookie cookie = new Cookie(CookieUtil.getPassportCookieName(deployMode),passport.getId() + "");
        cookie.setPath("/");
        cookie.setMaxAge(7*24/3600);
        cookie.setDomain(UrlUtil.getSiteDomain(request));
        response.addCookie(cookie);
        return loginResponse;
    }

    /**
     * 退出登录
     */
    @ApiName("/system/logout.api")
    public static LogoutResponse logout(JSONObject jsonObject,Passport passport,HttpServletRequest request,HttpServletResponse response){
        LogoutRequest logoutRequest = JSON.toJavaObject(jsonObject,LogoutRequest.class);

        if(passport != null){
          return systemService.logout(logoutRequest,passport);
        }
        return new LogoutResponse();
    }

}
