package com.util.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求工具类
 * @author 唐小甫
 * @datetime 2020-12-05 19:06:49
 */
public class RequestUtil {

	/**
	 * 请求转发
	 * @param request
	 * @param response
	 * @param path
	 * @throws ServletException
	 * @throws IOException void
	 * @author 唐小甫
	 * @datetime 2020-12-05 19:06:34
	 */
	public static void sendForward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	
	/**
	 * 获取指定请求头信息
	 * @param request
	 * @param requestHeaderName
	 * @return String
	 * @author 唐小甫
	 * @datetime 2020-12-05 19:06:07
	 */
	public static String getRequestHeader(HttpServletRequest request, String requestHeaderName) {
	    return request.getHeader(requestHeaderName);
	}
	
	
	/**
	 * 获取请求头枚举类
	 * @param request
	 * @return Map<String,String>
	 * @author 唐小甫
	 * @datetime 2020-12-05 19:05:15
	 */
	@SuppressWarnings("unchecked")
    public static Map<String, String> getRequestHeaderMap(HttpServletRequest request) {
	    Enumeration<String> headerNames = request.getHeaderNames();
	    Map<String, String> map = new HashMap<String, String>(16);
        String headerName;
        while (headerNames.hasMoreElements()) {
            headerName = headerNames.nextElement();
            map.put(headerName, request.getHeader(headerName));
        }
        return map;
	}
}