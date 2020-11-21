package com.util.encryption;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密操作
 * @author 唐兴甫
 * @create 2019/10/18 16:32
 */
public class MD5Util {

    /**
     * MD5加密
     * @param raw
     * @return java.lang.String
     * @author 唐小甫
     * @datetime 2020/11/21 23:31
     */
    public static String toMd5BigInteger(String raw) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            //计算明文md5的值
            md5.update(raw.getBytes());
            //1: 代表正数; 0: 代表0; -1: 代表负数
            return new BigInteger(0, md5.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}