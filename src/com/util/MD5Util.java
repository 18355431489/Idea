package com.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author 唐兴甫
 * @create 2019/10/18 16:32
 * @Version 1.0
 */
public class MD5Util {

    /**
     * MD5 加密方法
     */
    public static String toMd5(String rawPassword) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            //计算明文md5的值
            md5.update(rawPassword.getBytes());
            //1: 代表正数; 0: 代表0; -1: 代表负数
            return new BigInteger(1, md5.digest()).toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}