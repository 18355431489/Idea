package com.util.servlet;

import java.util.*;

/**
 * java类型工具类
 * @author 唐小甫
 * @datetime 2020-11-22 21:26:39
 */
public class JavaTypeUtil {

    /**
     * 获取对象的类型
     * @param t
     * @return com.util.json.JavaTypeUtil.TypeEnum
     * @author 唐小甫
     * @datetime 2020-11-22 21:34:49
     */
    public static <T> TypeEnum getClassType(T t) {
        if (t == null) {
            return TypeEnum.SIMPLE_TYPE;
        }
        if (t instanceof Byte) {
            return TypeEnum.SIMPLE_TYPE;
        }
        if (t instanceof Short) {
            return TypeEnum.SIMPLE_TYPE;
        }
        if (t instanceof Integer) {
            return TypeEnum.SIMPLE_TYPE;
        }
        if (t instanceof Long) {
            return TypeEnum.SIMPLE_TYPE;
        }
        if (t instanceof Character) {
            return TypeEnum.SIMPLE_TYPE;
        }
        if (t instanceof Boolean) {
            return TypeEnum.SIMPLE_TYPE;
        }
        if (t instanceof Float) {
            return TypeEnum.SIMPLE_TYPE;
        }
        if (t instanceof Double) {
            return TypeEnum.SIMPLE_TYPE;
        }
        if (t instanceof String) {
            return TypeEnum.SIMPLE_TYPE;
        }
        if (t instanceof Date) {
            return TypeEnum.DATE_TYPE;
        }
        return getComplexClassType(t);
    }


    /**
     * 获取对象详细类型
     * @param t
     * @return com.util.json.JavaTypeUtil.TypeEnum
     * @author 唐小甫
     * @datetime 2020-11-22 21:36:49
     */
    public static <T> TypeEnum getDeclaredClassType(T t) {
        if (t == null) {
            return TypeEnum.NULL_TYPE;
        }
        if (t instanceof Byte) {
            return TypeEnum.BYTE_TYPE;
        }
        if (t instanceof Short) {
            return TypeEnum.SHORT_TYPE;
        }
        if (t instanceof Integer) {
            return TypeEnum.INTEGER_TYPE;
        }
        if (t instanceof Long) {
            return TypeEnum.LONG_TYPE;
        }
        if (t instanceof Character) {
            return TypeEnum.CHARACTER_TYPE;
        }
        if (t instanceof Boolean) {
            return TypeEnum.BOOLEAN_TYPE;
        }
        if (t instanceof Float) {
            return TypeEnum.FLOAT_TYPE;
        }
        if (t instanceof Double) {
            return TypeEnum.DOUBLE_TYPE;
        }
        if (t instanceof String) {
            return TypeEnum.STRING_TYPE;
        }
        if (t instanceof Date) {
            return TypeEnum.DATE_TYPE;
        }
        return getComplexClassType(t);
    }


    /**
     * 复杂对象类型
     * @param <T>
     * @param t
     * @return TypeEnum
     * @author 唐小甫
     * @datetime 2020-11-23 22:59:15
     */
    public static <T> TypeEnum getComplexClassType(T t) {
        
        if (t instanceof Collection) {
            return TypeEnum.COLLECTION_TYPE;
        }
        if (t instanceof Map) {
            return TypeEnum.MAP_TYPE;
        }
        if (t instanceof Object[]) {
            return TypeEnum.ARRAY_TYPE;
        }
        return TypeEnum.OBJECT_TYPE;
    }


    /**
     * 根据简写类型名称返回对象类型
     * @param typeName
     * @return TypeEnum
     * @author 唐小甫
     * @datetime 2020-11-23 22:59:41
     */
    public static TypeEnum getDeclaredClassTypeByTypeName(String typeName) {
        
        if (typeName == null) {
            return TypeEnum.NULL_TYPE;
        }
        if (typeName.contains("[")) {
            return TypeEnum.ARRAY_TYPE;
        }
        if (typeName.equals("List")) {
            return TypeEnum.COLLECTION_TYPE;
        }
        if (typeName.equals("Set")) {
            return TypeEnum.COLLECTION_TYPE;
        }
        if (typeName.equals("Map")) {
            return TypeEnum.MAP_TYPE;
        }
        if (typeName.equals("Date")) {
            return TypeEnum.DATE_TYPE;
        }
        if (typeName.equals("byte") || typeName.contains("Byte")) {
            return TypeEnum.BYTE_TYPE;
        }
        if (typeName.equals("short") || typeName.contains("Short")) {
            return TypeEnum.SHORT_TYPE;
        }
        if (typeName.equals("int") || typeName.contains("Integer")) {
            return TypeEnum.INTEGER_TYPE;
        }
        if (typeName.equals("long") || typeName.contains("Long")) {
            return TypeEnum.LONG_TYPE;
        }
        if (typeName.equals("float") || typeName.contains("Float")) {
            return TypeEnum.FLOAT_TYPE;
        }
        if (typeName.equals("double") || typeName.contains("Double")) {
            return TypeEnum.DOUBLE_TYPE;
        }
        if (typeName.equals("char") || typeName.contains("Character")) {
            return TypeEnum.CHARACTER_TYPE;
        }
        if (typeName.equals("boolean") || typeName.contains("Boolean")) {
            return TypeEnum.BOOLEAN_TYPE;
        }
        return TypeEnum.STRING_TYPE;
    }


    /**
     * Java类型枚举
     * @author 唐小甫
     * @datetime 2020-11-22 21:27:49
     */
    enum TypeEnum {
        /** 简单类型 */
        SIMPLE_TYPE,
        /** 字节类型 */
        BYTE_TYPE,
        /** 短整型 */
        SHORT_TYPE,
        /** 整型 */
        INTEGER_TYPE,
        /** 长整型 */
        LONG_TYPE,
        /** 单精度浮点类型 */
        FLOAT_TYPE,
        /** 双精度浮点类型 */
        DOUBLE_TYPE,
        /** 字符类型 */
        CHARACTER_TYPE,
        /** 布尔类型 */
        BOOLEAN_TYPE,
        /** 日期类型 */
        DATE_TYPE,
        /** 字符串类型 */
        STRING_TYPE,
        /** 数组类型 */
        ARRAY_TYPE,
        /** 单列集合类型 */
        COLLECTION_TYPE,
        /** 双列集合类型 */
        MAP_TYPE,
        /** 对象类型 */
        OBJECT_TYPE,
        /** 空类型 */
        NULL_TYPE;
    }
}