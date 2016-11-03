package com.alibaba.framework.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by 马永龙 on 2016/10/3.
 */
public class StringUtil {

    public static int getByteLength(String str) {
        int length = str.replaceAll("[^\\x00-\\xff]", "**").length();
        return length;
    }

    public static String upperFirstWord(String str) {
        String temp = str.substring(0, 1);
        return temp.toUpperCase() + str.substring(1);
    }

    /**
     * TODO
     *
     * @param str
     * @param reg
     * @param index
     * @return
     */
    public static String split(String str, String reg, int index) {
        if(reg.length() == 0) {
            return str;
        }
        String[] strings = str.split(reg);
        if(index < 0) {
            index = 0;
        }
        if(index >= strings.length) {
            index = strings.length - 1;
        }
        return strings[index];
    }

    public static String substring(String str, int start, int end) {
        if(0 >= start || start >= str.length()) {
            end = str.length() - 1;
        }
        if(0 >= end || end >= str.length()) {
            end = str.length() - 1;
        }
        return str.substring(start, end);
    }


    /**
     * 将字符串加密处理,例：sample@xiniunet.com → samp***********.com
     *
     * @param input       想要加密的字符串
     * @param startLength 加密开始位置
     * @param endLength   加密结束位置
     * @param isAbsolute  是否保持原来长度
     * @return 加密后的字符串
     */
    public static String encryptString(String input, int startLength, int endLength, boolean isAbsolute) {
        int length = input.length();
        int endIndex = length - endLength;
        if(startLength > length || endLength > length) {
            return input;
        }

        String start = input.substring(0, startLength);
        String end = input.substring(endIndex);
        String out = start;

        if(isAbsolute) {
            for(int i = startLength; i < endIndex; i++) {
                out += "*";
            }
        } else {
            out += "****";
        }
        out += end;

        return out;
    }

    public static String fixNumberLength(int number, int length) {
        String fixedNumber = "" + String.valueOf(number);
        while(fixedNumber.length() < length) {
            fixedNumber = "0" + fixedNumber;
        }
        return fixedNumber;
    }
    /**
     * 将emoji表情替换成*
     *
     * @param source
     * @return 过滤后的字符串
     */
    public static String filterEmoji(String source) {
        if(StringUtils.isNotBlank(source)){
            return source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "");
        }else{
            return source;
        }
    }

    public static void main(String[] arg ){
        try{
            String text = "This is a smiley \uD83C\uDFA6 face\uD860\uDD5D \uD860\uDE07 \uD860\uDEE2 \uD863\uDCCA \uD863\uDCCD \uD863\uDCD2 \uD867\uDD98 ";
            System.out.println(text);
            System.out.println(text.length());
            System.out.println(text.replaceAll("[\\ud83c\\udc00-\\ud83c\\udfff]|[\\ud83d\\udc00-\\ud83d\\udfff]|[\\u2600-\\u27ff]", ""));
            System.out.println(filterEmoji(text));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
