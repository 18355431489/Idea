package com.util;

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
                map.put(StringUtil.sqlNameTohumpName(columnName), columnValue);
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
            String fieldName = StringUtil.setMethodNameToFieldName(methodName);
            if (!fieldName.equals(StringUtil.sqlNameTohumpName(columnName))) {
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
}