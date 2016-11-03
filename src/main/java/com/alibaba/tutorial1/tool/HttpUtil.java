package com.alibaba.tutorial1.tool;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;

/**
 * Created by 马永龙 on 2016/10/3.
 */
public class HttpUtil {

    public static String get(String url) throws Exception{

        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpgets = new HttpGet(url);
        try {
            HttpResponse response = httpclient.execute(httpgets);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                // 将流转化为字符串后输出
                return convertStreamToString(entity.getContent());
            }
        }catch(Exception ex){
            ex.printStackTrace();
            throw ex;
        }finally {
            httpgets.abort();
        }
        return "";
    }

    public static String get(String url,Map<String,String> param) throws Exception{

        StringBuilder stringBuilder = new StringBuilder("");
        Set<String> paramKeyList;
        String fullUrl = url;
        // 如果参数不为空，则把参数拼成queryString传递给get(url)方法
        if(param!=null && param.size()>0) {
            stringBuilder.append("?");
            paramKeyList = param.keySet();
            for(String paramKey : paramKeyList){
                stringBuilder.append(paramKey).append("=").append(param.get(paramKey)).append("&");
            }
            String queryString = stringBuilder.toString();
            // 去除最后一个"&"符号
            queryString = queryString.substring(0, queryString.length() - 1);
            fullUrl += queryString;
        }
        return get(fullUrl);
    }

    private static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
