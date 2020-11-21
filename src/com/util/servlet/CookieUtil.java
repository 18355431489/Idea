package com.util.servlet;

import javax.servlet.http.Cookie;

/**
 * @describe: Cookie工具
 * @author 唐兴甫
 */
public class CookieUtil {
	
	/**
	 * @describe: 客户端Session失效
	 * @return
	 */
	public static Cookie setInvalidateSession() {
		return setInvalidateCookie("JSESSIONID");
	}
	
	
	/**
	 * @describe: 客户端Session持久化时间为自定义时间
	 * @param requst
	 * @return
	 */
	public static Cookie setSession(String sessionId, Integer maxAge) {
		return setCookie("JSESSIONID", sessionId, maxAge);
	}
	
	
	/**
	 * @describe: 客户端Session持久化时间为会话级别
	 * @param requst
	 * @return
	 */
	public static Cookie setSession(String sessionId) {
		return setCookie("JSESSIONID", sessionId);
	}
	
	
	/**
	 * @describe: 设置客户端Cookie失效
	 * @param cookieName
	 * @param cookieValue
	 * @return
	 */
	public static Cookie setInvalidateCookie(String cookieName) {
		return setCookie(cookieName, null, 0);
	}
	
	
	/**
	 * @describe: 设置客户端Cookie信息和会话级别
	 * @param cookieName
	 * @param cookieValue
	 * @return
	 */
	public static Cookie setCookie(String cookieName, String cookieValue) {
		return setCookie(cookieName, cookieValue, -1);
	}
	

	/**
	 * @describe: 设置客户端Cookie信息和Cookie持久级别
	 * @param cookieName
	 * @param cookieValue
	 * @param maxAge: 默认为10天
	 * @return
	 */
	public static Cookie setCookie(String cookieName, String cookieValue, Integer maxAge) {
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setMaxAge(maxAge);
		return cookie;
	}
}