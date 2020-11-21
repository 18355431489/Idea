package com.util.encryption;

import java.util.Base64;

/**
 * Base64加密/解密工具
 * @author 唐兴甫
 * @create 2019/10/18 17:38
 */
public class Base64Util {

    /**
     * 文本加密
     * @param raw
     * @return java.lang.String
     * @author 唐小甫
     * @datetime 2020/11/21 23:31
     */
    public static String encode(String raw){
        return Base64.getEncoder().encodeToString(raw.getBytes());
    }


    /**
     * 媒体加密
     * @param bytes
     * @return java.lang.String
     * @author 唐小甫
     * @datetime 2020/11/22 0:00
     */
    public static String encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }


    /**
     * 文本解密
     * @param cooked
     * @return java.lang.String
     * @author 唐小甫
     * @datetime 2020/11/21 23:32
     */
    public static String decode(String cooked) {
        return new String(Base64.getDecoder().decode(cooked));
    }


    /**
     * 媒体解密
     * @param cooked
     * @return java.lang.String
     * @author 唐小甫
     * @datetime 2020/11/21 23:32
     */
    public static byte[] decode(byte[] cooked) {
        return Base64.getDecoder().decode(cooked);
    }
}