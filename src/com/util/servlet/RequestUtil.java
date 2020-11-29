package com.util.servlet;

import com.util.servlet.JavaTypeUtil;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @describe: 请求工具(未完成)
 * @author: 唐小甫
 * @datetime: 2020-05-14 22:23:22
 * @version: 1.0
 */
public class RequestUtil {

	/**
	 * @describe: 请求转发
	 * @param request
	 * @param response
	 * @param path
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void sendForward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	
	/**
	 * @describe: 请求域中获取参数封装到对象中
	 * @param <T>
	 * @param request
	 * @param clazz
	 * @return
	 */
	public static <T extends Object> T getParameterObject(HttpServletRequest request, Class<T> clazz) {
		try {
			Field[] fields = clazz.getDeclaredFields();
			T instance = clazz.getConstructor().newInstance();
			for (Field field : fields) {
				voluation(instance, request, field, clazz);
			}
			return instance;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * @describe: 数据注入
	 * @param <T>
	 * @param instance
	 * @param request
	 * @param field
	 * @param clazz
	 * @throws Exception
	 */
	private static <T> void voluation(T instance, HttpServletRequest request, Field field, Class<T> clazz) throws Exception {
		String fieldName = field.getName();
		char ch = fieldName.charAt(0);
		String setMethodName = "set" + fieldName.replace(ch, Character.toUpperCase(ch));
		Class<?> typeClazz = field.getType();
		Method setMethod = clazz.getMethod(setMethodName, typeClazz);
		Object value = request.getParameter(fieldName);
		if (value == null) {
			return;
		}
		if (simpleInjection(instance, typeClazz.getSimpleName(), setMethod, value)) {
			return;
		}
		complexInjuection(instance, typeClazz.getSimpleName(), setMethod, request, fieldName);
	}
	
	
	/**
	 * @describe: 复杂类型注入(未完成)
	 * @param <T>
	 * @param instance
	 * @param simpleName
	 * @param setMethod
	 * @param request
	 * @param fieldName
	 * @throws Exception
	 */
	private static <T> void complexInjuection(T instance, String simpleName, Method setMethod, HttpServletRequest request, String fieldName) throws Exception {
//		String[] values = request.getParameterValues(fieldName);
//		switch (JavaTypeUtil.getDeclaredClassTypeByTypeName(simpleName)) {
//		case LISTTYPE:
//			break;
//		case SETTYPE:
//			Type pType = setMethod.getGenericParameterTypes()[0];
//			Class<?> clazz = (Class<?>) pType;
//			break;
//		case MAPTYPE:
//			break;
//		case ARRAYTYPE:
//			break;
//		default: break;
//		}
	}


	
	/**
	 * @describe: 简单类型注入
	 * @param <T>
	 * @param instance
	 * @param simpleName
	 * @param setMethod
	 * @param value
	 * @return
	 * @throws Exception
	 */
	private static <T> Boolean simpleInjection(T instance, String simpleName, Method setMethod, Object value) throws Exception {
		switch (JavaTypeUtil.getDeclaredClassTypeByTypeName(simpleName)) {
		case STRING_TYPE:
			setMethod.invoke(instance, value.toString());
			break;
		case BYTE_TYPE:
			setMethod.invoke(instance, Byte.valueOf(value.toString()));
			break;
		case SHORT_TYPE:
			setMethod.invoke(instance, Short.valueOf(value.toString()));
			break;
		case INTEGER_TYPE:
			setMethod.invoke(instance, Integer.valueOf(value.toString()));
			break;
		case LONG_TYPE:
			setMethod.invoke(instance, Long.valueOf(value.toString()));
			break;
		case FLOAT_TYPE:
			setMethod.invoke(instance, Float.valueOf(value.toString()));
			break;
		case DOUBLE_TYPE:
			setMethod.invoke(instance, Double.valueOf(value.toString()));
			break;
		case CHARACTER_TYPE:
			setMethod.invoke(instance, value.toString().charAt(0));
			break;
		case BOOLEAN_TYPE:
			setMethod.invoke(instance, Boolean.valueOf(value.toString()));
			break;
		case DATE_TYPE:
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
			setMethod.invoke(instance, sdf.parse(value.toString()));
			break;
		default:
			return false;
		}
		return true;
	}
}