package com.alibaba.tutorial1.tool;

import com.alibaba.tutorial1.modules.system.domain.Passport;
import com.alibaba.tutorial1.modules.system.domain.User;

import java.io.Serializable;

/**
 * Created by 马永龙 on 2016/9/21.
 */
public class LocalData implements Serializable {

    private static final ThreadLocal<Long> logIdHolder = new ThreadLocal<Long>();

    /**
     * 当前用户的护照信息
     */
    private static final ThreadLocal<Passport> passportHolder = new ThreadLocal<Passport>();

    /**
     * 当前访问用户的信息
     */
    private static final ThreadLocal<User> currencyUserHolder = new ThreadLocal<User>();

    public static String getLog(String log) {

        return "log" + LocalData.getLogId() + " - " + log;
    }

    public static String getLog(Throwable ex) {
        if(ex.getCause() != null) {
            return getLog("出现异常:[" + ex.getCause().getMessage() + "]");
        } else {
            return getLog("出现异常:[" + ex.getMessage() + "]");
        }
    }

    public static Long getLogId() {
        Long id = logIdHolder.get();
        if(id == null) {
            id = Sequence.getNewId();
            setLogId(id);
        }
        return id;
    }
    public static void setLogId(Long id) {
        logIdHolder.set(id);
    }

    @Override
    public String toString() {
        return "LocalData{}";
    }

    public static Passport getCurrentPassport() {
        return passportHolder.get();
    }

    public static void setCurrentPassport(Passport passport) {
        passportHolder.set(passport);
    }

    public static void setCurrentUser(User user) {
        currencyUserHolder.set(user);
    }

    public static User getCurrentUser() {
        return currencyUserHolder.get();
    }

}
