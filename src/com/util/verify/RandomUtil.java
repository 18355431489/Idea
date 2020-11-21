package com.util.verify;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 * @author 唐兴甫
 * @create 2019/10/18 17:24
 * @Version 1.0
 */
public class RandomUtil {

    /**
     * 获取当前时间并转换成字符串
     * @return
     */
    public static String getTime(){
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime());
    }

    /**
     * 生成验证码
     * @return
     */
    public static String createCode(){
        return getTime() + Integer.toHexString(new Random().nextInt(900) + 100);
    }
}
