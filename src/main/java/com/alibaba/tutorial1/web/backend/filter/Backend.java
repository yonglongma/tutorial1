package com.alibaba.tutorial1.web.backend.filter;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by 马永龙 on 2016/9/27.
 */
public class Backend implements Filter{
    ApplicationContext applicationContext;
    ServletContext servletContext;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        servletContext = filterConfig.getServletContext();
        applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //获取访问路径
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        String path = httpServletRequest.getRequestURI();
        String url = httpServletRequest.getRequestURL().toString();

        //获取域名
        String domain = url.split("://")[1].split("/")[0];

        /* 验证过滤器例外文件(css || js || 图片)，当文件名包含这些内容时，将直接放行，不予过滤。 */
        String[] suffixes = {".css", ".js", "$", "!", ".jpg", ".gif", ".png", ".ttf", ".ico", ".xls", ".woff", ".less"};
        for (String suffix : suffixes) {
            if (path.contains(suffix)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
