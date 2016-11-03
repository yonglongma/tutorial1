package com.alibaba.tutorial1.tool;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 马永龙 on 2016/10/2.
 */
public class EncryptUtil {

    /**
     * MD5加密
     * @param input
     * @return
     */
    public static String MD5(String input) {
        try {
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(input.getBytes());
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < md.length; i++) {
                String shaHex = Integer.toHexString(md[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * SHA1加密
     * @param input
     * @return
     */
    public static String SHA1(String input) {
        try {
            MessageDigest digest = MessageDigest
                    .getInstance("SHA-1");
            digest.update(input.getBytes());
            byte messageDigest[] = digest.digest();
            // Add Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] agrs) {
        String str = "CODE=COM.XINIU.ERP&NAME=犀牛ERP&ISV_ID=1";
        str = str.toUpperCase();
        String key = EncryptUtil.MD5(str);
        System.out.println(key);

        str += "&KEY=" + key;
        str = str.toUpperCase();
        System.out.println(str);

        String secret = EncryptUtil.MD5(str);
        System.out.println(secret);
    }
}
