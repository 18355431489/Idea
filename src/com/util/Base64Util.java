package com.util;

import java.util.Base64;

/**
 * Base64加密/解密工具
 * @author 唐兴甫
 * @create 2019/10/18 17:38
 * @Version 1.0
 */
public class Base64Util {

    /**
     * 编码-->加密
     * @param raw
     * @return
     */
    public static String encode(String raw){
        return Base64.getEncoder().encodeToString(raw.getBytes());
    }


	/**
	 * 解码-->解密
	 * @param msg
	 * @return
	 */
    public static String decode(String msg){
        return new String(Base64.getDecoder().decode(msg));
    }
}