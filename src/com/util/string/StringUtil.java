package com.util.string;

/**
 * 字符串操作
 * @author 唐小甫
 * @datetime 2020-11-21 15:13:08
 */
public class StringUtil {

    /** 空格字符串 */
    private static final String SPACE_STRING = " ";
    /** 空字符串 */
    public static final String EMPTY_STRING = "";


    /**
     * 字符串是否为空
     * @param str
     * @return boolean
     * @author 唐小甫
     * @datetime 2020-11-21 14:33:49
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }


    /**
     * 字符串内容是否为空
     * @param str
     * @return boolean
     * @author 唐小甫
     * @datetime 2020-11-21 14:46:49
     */
    public static boolean isBlank(String str) {
        if (str == null) {
            return true;
        }
        str = str.replaceAll(SPACE_STRING, EMPTY_STRING);
        return str.length() == 0;
    }
}