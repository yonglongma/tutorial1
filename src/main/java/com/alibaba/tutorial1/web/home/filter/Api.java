package com.alibaba.tutorial1.web.home.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.framework.util.StringUtil;
import com.alibaba.tutorial1.modules.system.domain.Passport;
import com.alibaba.framework.annotation.ApiName;
import com.alibaba.framework.base.BaseResponse;
import com.alibaba.tutorial1.tool.LocalData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 马永龙 on 2016/9/29.
 * 前台调用方法通用过滤器
 */
public class Api implements Filter {

    private Map<String,Class> classMap;

    private Map<String,Method> methodMap;

    private final Logger logger = LoggerFactory.getLogger(Api.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        classMap = new HashMap<String, Class>();
        methodMap = new HashMap<String, Method>();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        Passport passport = LocalData.getCurrentPassport();
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        //只接受post请求
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if(!httpServletRequest.getMethod().equalsIgnoreCase("POST")){
            return;
        }
        String referer = httpServletRequest.getHeader("referer");

        //获取要访问的路径
        final String path = httpServletRequest.getRequestURI(); //默认格式为 :/system/user/create.api

        String[] paths = path.split("/");
        // 请求路径一定为 斜杠 + 模块名 + 斜杠 + 方法路由 + .api ， 所以路径中至少包含两个斜杠
        if(paths.length < 3) {
            return;
        }
        String className = "com.alibaba.tutorial1.web.home.api." + StringUtil.upperFirstWord(paths[1]);
        Class clazz = dealClass(className);
        if(clazz == null) {
            return;
        }
        try {
            JSONObject jsonObject;
            InputStreamReader inputStreamReader = new InputStreamReader(request.getInputStream());
            BufferedReader in = new BufferedReader(inputStreamReader);
            String line = in.readLine();
            if(line != null && !line.isEmpty()){
                try {
                    jsonObject = JSON.parseObject(line);
                } catch (Exception e) {
                    String[] params = line.split("&");
                    jsonObject = new JSONObject();
                    for(String param :params){
                        String[] values = param.split("=");
                        if(values.length > 1){
                            String value = URLDecoder.decode(URLDecoder.decode(URLDecoder.decode(values[1], "UTF-8"), "UTF-8"), "UTF-8");
                            jsonObject.put(values[0],value);
                        }
                    }
                }
            }else{
                jsonObject = new JSONObject();
                Map<String,String[]> params = request.getParameterMap();
                for(String name :params.keySet()){
                    String[] values = params.get(name);
                    if(values != null && values.length > 0){
                        jsonObject.put(name,values[0]);
                    }
                }

            }

            //准备可能需要的参数
            Map<Class,Object> paramPool = new HashMap<Class, Object>();
            paramPool.put(JSONObject.class,jsonObject);
            paramPool.put(Passport.class,passport);
            paramPool.put(HttpServletRequest.class,request);
            paramPool.put(HttpServletResponse.class,response);

            Method method = methodMap.get(path);
            if(method != null){
                Class[] classes = method.getParameterTypes();
                Object[] params = new Object[classes.length];
                for(int i = 0;i< classes.length;i++){
                    params[i] = paramPool.get(classes[i]);
                }

                BaseResponse baseResponse = (BaseResponse)method.invoke(clazz,params);
                String json = JSON.toJSONString(baseResponse);
                if(baseResponse == null || baseResponse.hasError()){
                    logger.warn(LocalData.getLog("method : " + method.getName() + "  param : " + jsonObject + " response : " + json));
                }else{
                    logger.info(LocalData.getLog("method : " + method.getName() + "  param : " + jsonObject));
                }
                PrintWriter out = response.getWriter();
                out.println(json);
            }
        } catch (Exception e) {
            logger.error(LocalData.getLog(e), e);
        }
    }

    @Override
    public void destroy() {

    }

    /**
     * 根据方法所在的类，初始化classMap,methodMap
     * @param className
     * @return
     */
    private Class dealClass(String className) {
        Class clazz;
        if(classMap.get(className) == null) {
            try {
                clazz = Class.forName(className);
            } catch(ClassNotFoundException e) {
                logger.error(LocalData.getLog(e), e);
                return null;
            }
            classMap.put(className, clazz);

            //获取类中所有的方法
            Method[] methods = clazz.getDeclaredMethods();
            for(Method method : methods) {
                if(method.isAnnotationPresent(ApiName.class)) {
                    //如果方法上有注解，获取注解对象
                    ApiName apiName = method.getAnnotation(ApiName.class);
                    String value = apiName.value();
                    methodMap.put(value, method);
                }
            }
        } else {
            clazz = classMap.get(className);
        }
        return clazz;
    }
}
