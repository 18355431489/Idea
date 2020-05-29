package com.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @describe: 时间工具
 * @author 唐兴甫
 */
public class TimeUtil {
	
	private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * @describe: 获取当前时间
	 * @return
	 */
	public static Date getNow() {
		return new Date();
	}
	
	
	/**
	 * @describe: 获取当前时间的字符串
	 * @return
	 */
	public static String getNowString() {
		return dateToString(getNow(), PATTERN);
	}
	
	
	/**
	 * @describe: 获取当前时间的格式化字符串
	 * @param pattern
	 * @return
	 */
	public static String toFormatString(String pattern) {
		return dateToString(getNow(), pattern);
	}
	
	
	/**
	 * @describe: 字符串转化时间
	 * @param date
	 * @return
	 */
	public static Date toDate(String date) {
		return stringToDate(date, PATTERN);
	}
	
	
	/**
	 * @describe: 字符串解析成时间
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date stringToDate(String date, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		try {
			return simpleDateFormat.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * @describe: 时间转化字符串
	 * @param date
	 * @return
	 */
	public static String toString(Date date) {
		return dateToString(date, PATTERN);
	}

	
	/**
	 * @describe: 时间格式化字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToString(Date date, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		try {
			return simpleDateFormat.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}