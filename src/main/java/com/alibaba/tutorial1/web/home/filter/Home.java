package com.alibaba.tutorial1.web.home.filter;

import com.alibaba.tutorial1.modules.system.domain.Passport;
import com.alibaba.tutorial1.modules.system.domain.User;
import com.alibaba.tutorial1.modules.system.enumication.IdentityTypeEnum;
import com.alibaba.tutorial1.modules.system.request.PassportGetRequest;
import com.alibaba.tutorial1.modules.system.request.UserGetRequest;
import com.alibaba.tutorial1.modules.system.response.PassportGetResponse;
import com.alibaba.tutorial1.modules.system.response.UserGetResponse;
import com.alibaba.tutorial1.modules.system.service.SystemService;
import com.alibaba.tutorial1.tool.BaseUtil;
import com.alibaba.tutorial1.tool.CookieUtil;
import com.alibaba.tutorial1.tool.LocalData;
import com.alibaba.tutorial1.tool.Sequence;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by 马永龙 on 2016/9/27.
 * 主过滤器，本地线程存储登录用户信息
 */
public class Home implements Filter {

    ApplicationContext applicationContext;
    ServletContext servletContext;
    SystemService systemService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        servletContext = filterConfig.getServletContext();
        applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        systemService = applicationContext.getBean(SystemService.class);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //获取访问路径
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        String path = httpServletRequest.getRequestURI();
        String url = httpServletRequest.getRequestURL().toString();

        //获取域名
        String domain = url.split("://")[1].split("/")[0];

        //过滤后台访问
        if(path.startsWith("/backend/")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        /* 验证过滤器例外文件(css || js || 图片)，当文件名包含这些内容时，将直接放行，不予过滤。 */
        String[] suffixes = {".css", ".js", "$", "!", ".jpg", ".gif", ".png", ".ttf", ".ico", ".xls", ".woff", ".less"};
        for (String suffix : suffixes) {
            if (path.contains(suffix)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }

        String deployMode = BaseUtil.getDeployMode();
        //通过cookie获取passport的id
        Long passportId = CookieUtil.getPassportId(httpServletRequest.getCookies(),deployMode);
        Passport passport = null;
        User user = null;
        //判断passport
        if(passportId != null){
            //获取护照信息
            PassportGetRequest passportGetRequest = new PassportGetRequest();
            passportGetRequest.setId(passportId);
            PassportGetResponse passportGetResponse = systemService.getPassport(passportGetRequest);
            passport = passportGetResponse.getPassport();

            if(passport != null){
                //不是普通用户或已过期或已注销
                if(passport.getIdentityType() != IdentityTypeEnum.COMMON || passport.getExpireTime().getTime() <new Date().getTime() || passport.getRevokeType() != null){
                    passport = null;
                }
            }
        }
        if(passport != null){
            UserGetRequest userGetRequest = new UserGetRequest();
            userGetRequest.setId(passport.getUserId());
            UserGetResponse userGetResponse = systemService.getUser(userGetRequest,passport);
            user = userGetResponse.getUser();
        }else{
            //未登录，不是方法调用并且路径指向个人中心
            if(!path.endsWith(".api") && path.startsWith("/mine/")){
                ((HttpServletResponse) servletResponse).sendRedirect("/auth/login.htm?redirectUrl=" + url);
                return;
            }
        }
        LocalData.setCurrentUser(user);
        LocalData.setCurrentPassport(passport);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
