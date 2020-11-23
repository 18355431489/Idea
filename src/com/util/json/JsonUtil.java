package com.util.json;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * TODO: 待完成
 * Json工具类
 * @author 唐小甫
 * @datetime 2020-11-22 21:26:06
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
    
    /** 默认时间格式 */
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:dd";


    /**
     * 简单类型转JSON字符串
     * @param t 简单类型对象
     * @return 
     * @author 唐小甫
     * @datetime 2020-11-23 20:09:03
     */
    private static String parseSimple(Object t) {
        return QUOTE_MARKS + t + QUOTE_MARKS;
    }

    
    /**
     * Date转Json字符串
     * @param obj 时间类型对象
     * @return String
     * @author 唐小甫
     * @datetime 2020-11-23 21:31:00
     */
    private static String parseDate(Object obj) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        return QUOTE_MARKS + dateFormat.format(obj) + QUOTE_MARKS;
    }
    

    
    /**
     * 数组转JSON字符串
     * @param array 数组对象
     * @return String
     * @author 唐小甫
     * @createTime 2020-11-23 20:17:18
     */
    private static String parseArray(Object[] array) {
        StringBuilder json = new StringBuilder(LEFT_BRACKET);
        for (Object t : array) {
            json.append(getJson(t) + COMMA);
        }
        removeTailComma(json);
        return json.append(RIGHT_BRACKET).toString();
    }


    /**
     * 单列集合转JSON字符串
     * @param collection 单列集合对象
     * @return String
     * @author 唐小甫
     * @datetime 2020-11-23 20:46:25
     */
    private static String parseCollection(Collection<Object> collection) {
        StringBuilder json = new StringBuilder(LEFT_BRACKET);
        for (Object t : collection) {
            json.append(getJson(t) + COMMA);
        }
        removeTailComma(json);
        return json.append(RIGHT_BRACKET).toString();
    }


    /**
     * 双列集合转JSON字符串
     * @param map 双列集合
     * @return String
     * @author 唐小甫
     * @datetime 2020-11-23 21:13:11
     */
    private static String parseMap(Map<String, Object> map) {
        StringBuilder json = new StringBuilder();
        json.append(LEFT_BRACE);
        Set<String> keys = map.keySet();
        for (String key : keys) {
            json.append(QUOTE_MARKS + key + QUOTE_MARKS + COLON + getJson(map.get(key)) + COMMA);
        }
        removeTailComma(json);
        return json.append(RIGHT_BRACE).toString();
    }
    
    
    /**
     * 对象转JSON字符串
     * @param t
     * @return String
     * @author 唐小甫
     * @datetime 2020-11-23 21:15:27
     */
    private static String parseObject(Object obj) {
        StringBuilder json = new StringBuilder();
        json.append(LEFT_BRACE);
        Class<?> clazz = obj.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            if (methods[i].getParameterCount() > 0) {
                continue;
            }
            if (methods[i].getReturnType().getName().equals("void")) {
                continue;
            }
            int modifiers = methods[i].getModifiers() & 1;
            if (modifiers != 1) {
                continue;
            }
            String methodName = methods[i].getName();
            if (!methodName.startsWith("get")) {
                continue;
            }
            String fieldName = methodName.substring(3);
            char uppChar = fieldName.charAt(0);
            char lowChar = Character.toLowerCase(fieldName.charAt(0));
            fieldName = fieldName.replaceFirst(uppChar + "", lowChar + "");
            json.append(QUOTE_MARKS + fieldName + QUOTE_MARKS + COLON);
            Object value;
            try {
                value = methods[i].invoke(obj);
                json.append(getJson(value));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            json.append(COMMA);
        }
        removeTailComma(json);
        return json.append(RIGHT_BRACE).toString();
    }


    /**
     * 去除尾部逗号
     * @param json
     * @author 唐小甫
     * @datetime 2020-11-23 21:15:52
     */
    private static void removeTailComma(StringBuilder json) {
        if (json.toString().endsWith(COMMA)) {
            json.deleteCharAt(json.length() -1);
        }
    }


    /**
     * 对象转JSON字符串
     * @param obj
     * @return String
     * @author 唐小甫
     * @datetime 2020-11-23 21:17:11
     */
    @SuppressWarnings("unchecked")
    public static String getJson(Object obj) {
        switch (JavaTypeUtil.getClassType(obj)) {
            case SIMPLE_TYPE:
                return parseSimple(obj);
            case DATE_TYPE:
                return parseDate(obj);
            case COLLECTION_TYPE:
                return parseCollection((Collection<Object>) obj);
            case MAP_TYPE:
                return parseMap((Map<String, Object>) obj);
            case ARRAY_TYPE:
                return parseArray((Object[])obj);
            case OBJECT_TYPE:
            default:
                return parseObject(obj);
        }
    }


    /**
     * @describe: JSON字符串转对象(未完成)
     * @param <T>
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T encapsulateObject(String json, Class<T> clazz) {
        return null;
    }
}