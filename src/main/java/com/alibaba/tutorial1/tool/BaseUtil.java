package com.alibaba.tutorial1.tool;

import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by 马永龙 on 2016/9/21.
 */
public class BaseUtil {

    private static final Logger logger = LoggerFactory.getLogger(BaseUtil.class);

    /**
     * 读取指定配置文件里的指定属性(未指定文件时，默认为setting.properties)
     * @param propName 属性名
     * @return 格式化后的字符串
     */
    public static String getProperty(String propName){
        String filePath = "config.properties";

        Properties prop = new Properties();
        InputStream in = getCaller(4).getResourceAsStream("/" + filePath);
        String propValue = "";
        try {
            prop.load(in);
            propValue = prop.getProperty(propName).trim();
            propValue = new String(propValue.getBytes("ISO-8859-1"),"gbk");
        } catch (Exception e) {
            logger.error(LocalData.getLog(e), e);
            return propValue;
        }
        return propValue;
    }

    public static Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty("resource.loader", "class");
        properties.setProperty("class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        properties.setProperty(Velocity.ENCODING_DEFAULT, "UTF-8");
        properties.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        properties.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
        return properties;
    }

    /**
     * 使用线程堆栈信息获取方法的调用者类名
     * @return 调用者类名
     */
    public static Class getCaller(int level){
        StackTraceElement stack[] = Thread.currentThread().getStackTrace();
        Class clazz;
        try {
            String name = stack[level].getClassName();
            clazz = Class.forName(name);
        }catch(Exception e){
            logger.error(LocalData.getLog(e), e);
            clazz = Object.class;
        }
        return clazz;
    }

    public static String SMS_API_UID = getProperty(Constants.KEY_SMS_API_UID);
    public static String SMS_API_PWD = getProperty(Constants.KEY_SMS_API_PWD);
    public static String DEPLOY_MODE = getProperty(Constants.KEY_DEPLOY_MODE);
    public static String PASSPORT_COOKIE_NAME = CookieUtil.getPassportCookieName(DEPLOY_MODE);

    public static String getSmsApiUid() {
        return SMS_API_UID;
    }

    public static String getSmsApiPwd() {
        return SMS_API_PWD;
    }

    public static String getDeployMode() {
        return DEPLOY_MODE;
    }

    public static String getPassportCookieName() {
        return PASSPORT_COOKIE_NAME;
    }
}
