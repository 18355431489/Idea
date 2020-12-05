package com.util.jdbc;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 查询结果映射工具
 * @author 唐小甫
 * @datetime 2020-12-06 00:02:29
 */
public class MapperUtil {


    /**
     * 操作结果集，返回基本类型数据
     * @param <T>
     * @param resultSet
     * @return T
     * @author 唐小甫
     * @datetime 2020-12-05 23:35:46
     */
    @SuppressWarnings({ "unchecked" })
    public static <T> T rowMapperSimple(ResultSet resultSet) {
        try {
            return (T) resultSet.getObject(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 利用反射操作结果集，封装到自定义对象
     * @param <T>
     * @param resultSet
     * @param clazz
     * @return T
     * @author 唐小甫
     * @datetime 2020-12-05 23:35:29
     */
    public static <T> T rowMapperObject(ResultSet resultSet, Class<T> clazz) {
        try {
            Constructor<T> constructor = clazz.getConstructor();
            T instance = constructor.newInstance();
            Method[] methods = clazz.getMethods();
            // 获取结果集字段集合
            ResultSetMetaData metaData = resultSet.getMetaData();
            // 获取结果集字段数量
            int columnCount = metaData.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                dependencyInjection(instance, methods, resultSet, metaData.getColumnLabel(i + 1), i + 1);
            }
            return instance;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 操作结果集到封装Map
     * @param resultSet
     * @return Map<String,Object>
     * @author 唐小甫
     * @datetime 2020-12-05 23:35:13
     */
    public static Map<String, Object> rowMapperMap(ResultSet resultSet) {
        try {
            Map<String, Object> map = new HashMap<String, Object>(16);
            // 获取结果集字段集合
            ResultSetMetaData metaData = resultSet.getMetaData();
            // 获取结果集字段数量
            int columnCount = metaData.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                String columnName = metaData.getColumnLabel(i + 1);
                Object columnValue = resultSet.getObject(i + 1);
                map.put(MapperUtil.sqlNameTohumpName(columnName), columnValue);
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 依赖注入
     * @param <T>
     * @param instance
     * @param methods
     * @param resultSet
     * @param columnName
     * @param columnId
     * @throws Exception
     * @author 唐小甫
     * @datetime 2020-12-05 23:34:53
     */
    private static <T> void dependencyInjection(T instance, Method[] methods, ResultSet resultSet, String columnName, Integer columnId) throws Exception {
        for (Method method : methods) {
            String methodName = method.getName();
            if (!methodName.startsWith("resultSet")) {
                continue;
            }
            String fieldName = MapperUtil.setMethodNameToFieldName(methodName);
            if (!fieldName.equals(MapperUtil.sqlNameTohumpName(columnName))) {
                continue;
            }
            Parameter parameter = method.getParameters()[0];
            String parameterType = parameter.getType().toString().substring(16);
            if ("Integer".equals(parameterType)) {
                parameterType = "Int";
            }
			Class<ResultSet> clazz = ResultSet.class;
            Method getMethod = clazz.getMethod("get" + parameterType, int.class);
            method.invoke(instance, getMethod.invoke(resultSet, columnId));
        }
    }


    /**
     * 驼峰命名转换SQL属性
     * @param name
     * @return String
     * @author 唐小甫
     * @datetime 2020-12-05 23:33:34
     */
    public static String humpNameToSqlName(String name) {
        char ch = name.charAt(0);
        if (Character.isUpperCase(ch)) {
            name = name.replaceFirst(ch + "", Character.toLowerCase(ch) + "");
        }
        for (int i = 1; i < name.length(); i++) {
            ch = name.charAt(i);
            if (Character.isUpperCase(ch)) {
                name = name.replace(ch + "", "_" + Character.toLowerCase(ch));
            }
        }
        return name;
    }


    /**
     * SQL属性转换驼峰命名
     * @param name
     * @return String
     * @author 唐小甫
     * @datetime 2020-12-05 23:33:34
     */
    public static String sqlNameTohumpName(String name) {
        char ch;
        int index;
        while ((index = name.indexOf("_")) != -1) {
            if (index < name.length() - 1 && index > 0) {
                ch = name.charAt(index + 1);
                name = name.replaceAll("_" + ch, "" + Character.toUpperCase(ch));
            } else {
                name = name.replaceFirst("_", "");
            }
        }
        return name;
    }


    /**
     * get/set方法名转换成员名
     * @param methodName
     * @return String
     * @author 唐小甫
     * @datetime 2020-12-05 23:33:34
     */
    public static String setMethodNameToFieldName(String methodName) {
        String fieldName = methodName.substring(3);
        char ch = fieldName.charAt(0);
        return fieldName.replaceFirst(ch + "", Character.toLowerCase(ch) + "");
    }


    /**
     * 属性名转set方法名
     * @param fieldName
     * @return String
     * @author 唐小甫
     * @datetime 2020-12-05 23:33:34
     */
    public static String fieldNameToSetMethodName(String fieldName) {
        return fieldNameToMethodName(fieldName, "resultSet");
    }


    /**
     * 属性名转get方法名
     * @param fieldName
     * @return String
     * @author 唐小甫
     * @datetime 2020-12-05 23:33:34
     */
    public static String fieldNameToGetMethodName(String fieldName) {
        return fieldNameToMethodName(fieldName, "get");
    }


    /**
     * 属性名转方法名+方法前缀
     * @param fieldName
     * @param methodPreName
     * @return String
     * @author 唐小甫
     * @datetime 2020-12-05 23:33:34
     */
    public static String fieldNameToMethodName(String fieldName, String methodPreName) {
        char ch = fieldName.charAt(0);
        fieldName = fieldName.replaceFirst(ch + "", Character.toUpperCase(ch) + "");
        return methodPreName + fieldName;
    }
}