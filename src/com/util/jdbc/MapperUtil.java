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
 * @author: 唐小甫
 * @describe: 查询结果映射工具
 * @createTime: 2020-05-14 14:49:40
 * @version: 1.0
 */
public class MapperUtil {

    /**
     * @describe: 操作结果集，返回基本类型数据
     * @param <T>
     * @param set
     * @return
     */
    @SuppressWarnings({ "unchecked" })
    public static <T> T rowMapperSimple(ResultSet set) {
        try {
            return (T) set.getObject(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @describe: 利用反射操作结果集，封装到自定义对象
     * @param <T>
     * @param set
     * @param clazz
     * @return
     */
    public static <T> T rowMapperObject(ResultSet set, Class<T> clazz) {
        try {
            Constructor<T> constructor = clazz.getConstructor();
            T instance = constructor.newInstance();
            Method[] methods = clazz.getMethods();
            // 获取列集
            ResultSetMetaData metaData = set.getMetaData();
            // 获取列数
            int columnCount = metaData.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                dependencyInjection(instance, methods, set, metaData.getColumnLabel(i + 1), i + 1);
            }
            return instance;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @describe: 操作结果集到封装Map
     * @param set
     * @return
     */
    public static Map<String, Object> rowMapperMap(ResultSet set) {
        try {
            Map<String, Object> map = new HashMap<String, Object>(16);
            // 获取列集
            ResultSetMetaData metaData = set.getMetaData();
            // 获取列数
            int columnCount = metaData.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                String columnName = metaData.getColumnLabel(i + 1);
                Object columnValue = set.getObject(i + 1);
                map.put(MapperUtil.sqlNameTohumpName(columnName), columnValue);
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @describe: 依赖注入
     * @param instance
     * @param methods
     * @param set
     * @param columnName
     * @param columnId
     * @param <T>
     * @throws Exception
     */
    private static <T> void dependencyInjection(T instance, Method[] methods, ResultSet set, String columnName, Integer columnId) throws Exception {
        for (Method method : methods) {
            String methodName = method.getName();
            if (!methodName.startsWith("set")) {
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
            method.invoke(instance, getMethod.invoke(set, columnId));
        }
    }





    /**
     * @describe: 驼峰命名转换SQL属性
     * @param name
     * @return
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
     * @describe: SQL属性转换驼峰命名
     * @param name
     * @return
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
     * @describe: get/set方法名转换成员名
     * @param methodName
     * @return
     */
    public static String setMethodNameToFieldName(String methodName) {
        String fieldName = methodName.substring(3);
        char ch = fieldName.charAt(0);
        return fieldName.replaceFirst(ch + "", Character.toLowerCase(ch) + "");
    }


    /**
     * @describe: 属性名转set方法名
     * @param fieldName
     * @return
     */
    public static String fieldNameToSetMethodName(String fieldName) {
        return fieldNameToMethodName(fieldName, "set");
    }


    /**
     * @describe: 属性名转get方法名
     * @param fieldName
     * @return
     */
    public static String fieldNameToGetMethodName(String fieldName) {
        return fieldNameToMethodName(fieldName, "get");
    }


    /**
     * @describe: 属性名转方法名+方法前缀
     * @param fieldName
     * @param methodPreName
     * @return
     */
    public static String fieldNameToMethodName(String fieldName, String methodPreName) {
        char ch = fieldName.charAt(0);
        fieldName = fieldName.replaceFirst(ch + "", Character.toUpperCase(ch) + "");
        return methodPreName + fieldName;
    }
}