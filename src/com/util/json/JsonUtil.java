package com.util.json;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 唐小甫
 * @createTime 2020-11-22 21:26:06
 */
public class JsonUtil {

    /** 逗号 */
    private static final String COMMA 			= ",";
    /** 左大括号 */
    private static final String LEFT_BRACE 		= "{";
    /** 右大括号 */
    private static final String RIGHT_BRACE 	= "}";
    /** 左中括号 */
    private static final String LEFT_BRACKET 	= "[";
    /** 右中括号 */
    private static final String RIGHT_BRACKET 	= "]";
    /** 冒号 */
    private static final String COLON 			= ":";
    /** 引号 */
    private static final String QUOTE_MARKS 	= "\"";



    /**
     * @describe: 简单类型解析成JSON字符串
     * @param <T>
     * @param t
     * @return
     */
    private static <T> String parseSimple(T t) {
        return QUOTE_MARKS + t + QUOTE_MARKS;
    }


    /**
     * @describe: 数组解析成JSON字符串
     * @param <T>
     * @param array
     * @return
     */
    private static <T> String parseArray(T[] array) {
        StringBuilder json = new StringBuilder(LEFT_BRACKET);
        for (T t : array) {
            json.append(getJson(t) + COMMA);
        }
        removeTailComma(json);
        return json.append(RIGHT_BRACKET).toString();
    }


    /**
     * @describe: 对象解析成JSON字符串
     * @param <T>
     * @param t
     * @return
     */
    private static <T> String parseObject(T t) {
        StringBuilder json = new StringBuilder();
        json.append(LEFT_BRACE);
        Class<?> clazz = t.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                String fieldName = field.getName();
                char ch = fieldName.charAt(0);
                String getMethodName = "get" + fieldName.replace(ch, Character.toUpperCase(ch));
                Method method = clazz.getMethod(getMethodName);
                if (method == null) {
                    continue;
                }
                Object obj = method.invoke(t);
                json.append(QUOTE_MARKS + fieldName + QUOTE_MARKS + COLON);
                json.append(getJson(obj));
                json.append(COMMA);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        removeTailComma(json);
        return json.append(RIGHT_BRACE).toString();
    }


    /**
     * @describe: 单列集合解析成JSON字符串
     * @param <T>
     * @param collection
     * @return
     */
    private static <T> String parseCollection(Collection<T> collection) {
        StringBuilder json = new StringBuilder(LEFT_BRACKET);
        for (T t : collection) {
            json.append(getJson(t) + COMMA);
        }
        removeTailComma(json);
        return json.append(RIGHT_BRACKET).toString();
    }


    /**
     * @describe: 双列集合解析成JSON字符串
     * @param map
     * @return
     */
    private static String parseMap(Map<String, Object> map) {
        StringBuilder json = new StringBuilder();
        json.append(LEFT_BRACE);
        Set<String> keys = map.keySet();
        for (String key : keys) {
            json.append(QUOTE_MARKS + key + QUOTE_MARKS + COLON);
            json.append(getJson(map.get(key)));
            json.append(COMMA);
        }
        removeTailComma(json);
        return json.append(RIGHT_BRACE).toString();
    }


    /**
     * @describe: 去除尾部逗号
     * @param json
     */
    private static void removeTailComma(StringBuilder json) {
        if (json.toString().endsWith(COMMA)) {
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
            case OBJECT_TYPE:
                return parseObject(obj);
            case COLLECTION_TYPE:
                return parseCollection((Collection<Object>) obj);
            case MAP_TYPE:
                return parseMap((Map<String, Object>) obj);
            case ARRAY_TYPE:
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