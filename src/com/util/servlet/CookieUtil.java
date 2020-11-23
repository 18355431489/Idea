package com.util.servlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Cookie工具
 * @author 唐小甫
 * @datetime 2020-11-23 23:05:31
 */
public class CookieUtil {
    
    /** 客户端默认失效时间，将cookie存储在浏览器内存中，关闭浏览器，cookie就会消失 */
    public static final int DEFAULT_EXPIRY = -1;
    /** 浏览器内存中删除此cookie */
    public static final int EXPIRY = 0;
    /** 此cookie会存储到客户端电脑，以cookie文件形式保存，
     * 不论关闭浏览器或关闭电脑，都不会消失，
     * 设置清除浏览器cookie缓存或到达过期时间才会过期 */
    public static final int ONE_HOUR_EXPIRY = 3600;
    
    /** 存储在客户端中的cookie名称 */
    public static final String JSESSIONID = "JSESSIONID";
    
    
	/**
	 * 客户端Session持久化时间为自定义时间
	 * @param sessionId
	 * @param maxAge
	 * @return Cookie
	 * @author 唐小甫
	 * @datetime 2020-11-23 23:22:07
	 */
	public static Cookie setSession(String sessionId, Integer maxAge) {
		return setCookie(JSESSIONID, sessionId, maxAge);
	}
	
	
	/**
	 * 设置客户端指定Cookie失效
	 * @param cookieName
	 * @return Cookie
	 * @author 唐小甫
	 * @datetime 2020-11-23 23:19:57
	 */
	public static Cookie setInvalidateCookie(String cookieName) {
		return setCookie(cookieName, null, EXPIRY);
	}
	
	
	/**
	 * 设置客户端Cookie信息
	 * @param cookieName
	 * @param cookieValue
	 * @return Cookie
	 * @author 唐小甫
	 * @datetime 2020-11-23 23:07:29
	 * @see #setCookie(String, String, Integer)
	 */
	public static Cookie setCookie(String cookieName, String cookieValue) {
		return setCookie(cookieName, cookieValue, DEFAULT_EXPIRY);
	}
	

	/**
	 * 设置客户端Cookie信息和Cookie持久级别
	 * @param cookieName   cookie名称
	 * @param cookieValue  cookie值
	 * @param age          cookie缓存时间(秒)
	 * @return Cookie
	 * @author 唐小甫
	 * @datetime 2020-11-23 23:06:25
	 * @see #DEFAULT_EXPIRY
	 * @see #EXPIRY
	 * @see #ONE_HOUR_EXPIRY
	 */
	public static Cookie setCookie(String cookieName, String cookieValue, Integer age) {
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setMaxAge(age);
		return cookie;
	}
	
	
	/**
	 * 获取指定cookie对象
	 * @param request
	 * @param cookieName
	 * @return Object
	 * @author 唐小甫
	 * @datetime 2020-11-23 23:33:40
	 */
	public static Object getCookie(HttpServletRequest request, String cookieName) {
	    Cookie[] cookies = request.getCookies();
	    if (cookies == null) {
	        return null;
	    }
	    for (int i = 0; i < cookies.length; i++) {
            if (cookieName.equals(cookies[i].getName())) {
                return cookies[i].getValue();
            }
        }
	    return null;
	}
}