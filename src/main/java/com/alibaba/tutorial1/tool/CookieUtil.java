package com.alibaba.tutorial1.tool;

import javax.servlet.http.Cookie;

/**
 * Created by 马永龙 on 2016/10/3.
 */
public class CookieUtil {

    public static String getPassportCookieName(String deployMode){
        if (deployMode.equals("production")) {
            return "PASSPORT_ID";
        } else {
            return "PASSPORT_ID_" + deployMode.toUpperCase();
        }
    }

    public static String getAdminCookieName(String deployMode){
        if (deployMode.equals("production")) {
            return "ADMIN_ID";
        } else {
            return "ADMIN_ID_" + deployMode.toUpperCase();
        }
    }

    /**
     * 通过Cookies获取passport的ID
     * @param cookies       Cookies
     * @return      passportID
     */
    public static Long getPassportId(Cookie[] cookies, String deployMode) {
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                // Cookie中 存放的为passport的id ，Cookie名称通过ConfigToolObject获取
                if (cookie != null && cookie.getName().equals(getPassportCookieName(deployMode))) {
                    try {
                        // 将cookie中的passportId转换为Long型
                        return Long.parseLong(cookie.getValue());
                    } catch (Exception ignored) {
                    }
                }
            }
        }
        return null;
    }

    /**
     * 通过Cookies获取passport的ID
     * @param cookies       Cookies
     * @return      passportID
     */
    public static Long getAdminPassportId(Cookie[] cookies, String deployMode) {
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                // Cookie中 存放的为passport的id ，Cookie名称通过ConfigToolObject获取
                if (cookie != null && cookie.getName().equals(getAdminCookieName(deployMode))) {
                    try {
                        // 将cookie中的passportId转换为Long型
                        return Long.parseLong(cookie.getValue());
                    } catch (Exception ignored) {
                    }
                }
            }
        }
        return null;
    }

    /**
     * 通过Cookies获取内容
     * @param cookies       Cookies
     * @return      passportID
     */
    public static String getCookieValue(Cookie[] cookies, String key) {
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                // Cookie中 存放的为passport的id ，Cookie名称通过ConfigToolObject获取
                if (cookie != null && cookie.getName().equals(key)) {
                    try {
                        return cookie.getValue();
                    } catch (Exception ignored) {
                    }
                }
            }
        }
        return null;
    }
}
