package com.alibaba.tutorial1.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 马永龙 on 2016/10/3.
 */
public class UrlUtil {

    private static final Logger logger = LoggerFactory.getLogger(UrlUtil.class);

    private UrlUtil() {

    }

    /**
     * 获取根路径
     */
    public static String getSiteDomain(String url) {
        String host = "";// 此处获取值转换为小写
        try {
            host = new URL(url).getHost().toLowerCase();
            Pattern pattern = Pattern.compile("[^\\.]+(\\.com\\.cn|\\.net\\.cn|\\.org\\.cn|\\.gov\\.cn|\\.com|\\.net|\\.cn|\\.org|\\.cc|\\.me|\\.tel|\\.mobi|\\.asia|\\.biz|\\.info|\\.name|\\.tv|\\.hk|\\.公司|\\.中国|\\.网络)");
            Matcher matcher = pattern.matcher(host);
            while (matcher.find()) {
                return matcher.group();
            }
        } catch (MalformedURLException e) {
            logger.error(LocalData.getLog(e), e);
        }
        return host;
    }

    public static String getSiteDomain(HttpServletRequest request) {
        String basePath = request.getScheme() + "://" + request.getServerName();
        return getSiteDomain(basePath);
    }
}
