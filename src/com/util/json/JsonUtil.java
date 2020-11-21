package com.util.json;

import com.util.json.JavaTypeUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @describe: JSON解析工具(未完成)
 * @author 唐兴甫
 */
public class JsonUtil {
	
	
	/**
	 * @describe: 简单类型解析成JSON字符串
	 * @param <T>
	 * @param t
	 * @return
	 */
	private static <T> String parseSimple(T t) {
		return "\"" + t + "\"";
	}
	
	
	/**
	 * @describe: 数组解析成JSON字符串
	 * @param <T>
	 * @param array
	 * @return
	 */
	private static <T> String parseArray(T[] array) {
		StringBuilder json = new StringBuilder("[");
		for (T t : array) {
			json.append(getJson(t) + ",");
		}
		removeTailComma(json);
		return json.append("]").toString();
	}
	

	/**
	 * @describe: 对象解析成JSON字符串
	 * @param <T>
	 * @param t
	 * @return
	 */
	private static <T> String parseObject(T t) {
		StringBuilder json = new StringBuilder();
		json.append("{");
		Class<?> clazz = t.getClass();
		Field[] fields = clazz.getDeclaredFields();
		try {
			for (Field field : fields) {
				String fieldName = field.getName();
				char ch = fieldName.charAt(0);
				String getMethodName = "get" + fieldName.replace(ch, Character.toUpperCase(ch));
				//getMethodName = StringUtil.fieldNameToGetMethodName(fieldName);
				Method method = clazz.getMethod(getMethodName);
				if (method == null) {
					continue;
				}
				Object obj = method.invoke(t);
				json.append("\"" + fieldName + "\": ");
				json.append(getJson(obj));
				json.append(",");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		removeTailComma(json);
		return json.append("}").toString();
	}
	
	
	/**
	 * @describe: list集合解析成JSON字符串
	 * @param <T>
	 * @param list
	 * @return
	 */
	private static <T> String parseList(List<T> list) {
		StringBuilder json = new StringBuilder("[");
		for (T t : list) {
			json.append(getJson(t) + ",");
		}
		removeTailComma(json);
		return json.append("]").toString();
	}
	
	
	/**
	 * @describe: set集合解析成JSON字符串
	 * @param <T>
	 * @param set
	 * @return
	 */
	private static <T> String parseSet(Set<T> set) {
		StringBuilder json = new StringBuilder("[");
		for (T t : set) {
			json.append(getJson(t) + ",");
		}
		removeTailComma(json);
		return json.append("]").toString();
	}
	
	
	/**
	 * @describe: map集合解析成JSON字符串
	 * @param <T>
	 * @param map
	 * @return
	 */
	private static <T> String parseMap(Map<String, Object> map) {
		StringBuilder json = new StringBuilder();
		json.append("{");
		Set<String> keys = map.keySet();
		for (String key : keys) {
			json.append("\"" + key + "\": ");
			json.append(getJson(map.get(key)));
			json.append(",");
		}
		removeTailComma(json);
		return json.append("}").toString();
	}
	
	
	/**
	 * @describe: 去除尾部逗号
	 * @param json
	 */
	private static void removeTailComma(StringBuilder json) {
		if (json.toString().endsWith(",")) {
			json.deleteCharAt(json.length() -1);
		}
	}
	
	
	/**
	 * @describe: 获取JSON字符串
	 * @param <T>
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> String getJson(Object obj) {
		switch (JavaTypeUtil.getClassType(obj)) {
		case OBJECTTYPE:
			return parseObject(obj);
		case LISTTYPE:
			return parseList((List<T>) obj);
		case SETTYPE:
			return parseSet((Set<T>) obj);
		case MAPTYPE:
			return parseMap((Map<String, Object>) obj);
		case ARRAYTYPE:
			return parseArray((T[])obj);
		default:
			return parseSimple(obj);
		}
	}
	
	
	/**
	 * @describe: JSON字符串解析成对象(未完成)
	 * @param <T>
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T encapsulateObject(String json, Class<T> clazz) {
		return null;
	}
}