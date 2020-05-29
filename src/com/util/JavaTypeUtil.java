package com.util;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @describe: Java对象类型工具
 * @author 唐兴甫
 */
public class JavaTypeUtil {

	/**
	 * @describe: 获取对象的类型
	 * @param <T>
	 * @param t
	 * @return
	 */
	public static <T> TypeEnum getClassType(T t) {
		if (t == null) {
			return TypeEnum.SIMPLETYPE;
		}
		if (t instanceof Byte) {
			return TypeEnum.SIMPLETYPE;
		}
		if (t instanceof Short) {
			return TypeEnum.SIMPLETYPE;
		}
		if (t instanceof Integer) {
			return TypeEnum.SIMPLETYPE;
		}
		if (t instanceof Long) {
			return TypeEnum.SIMPLETYPE;
		}
		if (t instanceof Character) {
			return TypeEnum.SIMPLETYPE;
		}
		if (t instanceof Boolean) {
			return TypeEnum.SIMPLETYPE;
		}
		if (t instanceof Float) {
			return TypeEnum.SIMPLETYPE;
		}
		if (t instanceof Double) {
			return TypeEnum.SIMPLETYPE;
		}
		if (t instanceof String) {
			return TypeEnum.SIMPLETYPE;
		}
		if (t instanceof Date) {
			return TypeEnum.SIMPLETYPE;
		}
		return getComplexClassType(t);
	}
	
	
	/**
	 * @describe: 获取对象详细类型
	 * @param <T>
	 * @param t
	 * @return
	 */
	public static <T> TypeEnum getDeclaredClassType(T t) {
		if (t == null) {
			return TypeEnum.NULLTYPE;
		}
		if (t instanceof Byte) {
			return TypeEnum.BYTETYPE;
		}
		if (t instanceof Short) {
			return TypeEnum.SHORTTYPE;
		}
		if (t instanceof Integer) {
			return TypeEnum.INTEGERTYPE;
		}
		if (t instanceof Long) {
			return TypeEnum.LONGTYPE;
		}
		if (t instanceof Character) {
			return TypeEnum.CHARACTERTYPE;
		}
		if (t instanceof Boolean) {
			return TypeEnum.BOOLEANTYPE;
		}
		if (t instanceof Float) {
			return TypeEnum.FLOATTYPE;
		}
		if (t instanceof Double) {
			return TypeEnum.DATETYPE;
		}
		if (t instanceof String) {
			return TypeEnum.STRINGTYPE;
		}
		if (t instanceof Date) {
			return TypeEnum.DATETYPE;
		}
		return getComplexClassType(t);
	}
	
	
	/**
	 * 集合对象类型
	 * @param t
	 * @param <T>
	 * @return
	 */
	public static <T> TypeEnum getComplexClassType(T t) {
		if (t instanceof List) {
			return TypeEnum.LISTTYPE;
		}
		if (t instanceof Set) {
			return TypeEnum.SETTYPE;
		}
		if (t instanceof Map) {
			return TypeEnum.MAPTYPE;
		}
		if (t instanceof Object[]) {
			return TypeEnum.ARRAYTYPE;
		}
		return TypeEnum.OBJECTTYPE;
	}
	
	
	/**
	 * @describe: 根据简写类型名称返回对象类型
	 * @param typeName
	 * @return
	 */
	public static TypeEnum getDeclaredClassTypeByTypeName(String typeName) {
		if (typeName == null) {
			return TypeEnum.NULLTYPE;
		}
		if (typeName.contains("[")) {
			return TypeEnum.ARRAYTYPE;
		}
		if (typeName.equals("List")) {
			return TypeEnum.LISTTYPE;
		}
		if (typeName.equals("Set")) {
			return TypeEnum.SETTYPE;
		}
		if (typeName.equals("Map")) {
			return TypeEnum.MAPTYPE;
		}
		if (typeName.equals("Date")) {
			return TypeEnum.DATETYPE;
		}
		if (typeName.equals("byte") || typeName.contains("Byte")) {
			return TypeEnum.BYTETYPE;
		}
		if (typeName.equals("short") || typeName.contains("Short")) {
			return TypeEnum.SHORTTYPE;
		}
		if (typeName.equals("int") || typeName.contains("Integer")) {
			return TypeEnum.INTEGERTYPE;
		}
		if (typeName.equals("long") || typeName.contains("Long")) {
			return TypeEnum.LONGTYPE;
		}
		if (typeName.equals("float") || typeName.contains("Float")) {
			return TypeEnum.FLOATTYPE;
		}
		if (typeName.equals("double") || typeName.contains("Double")) {
			return TypeEnum.DOUBLETYPE;
		}
		if (typeName.equals("char") || typeName.contains("Character")) {
			return TypeEnum.CHARACTERTYPE;
		}
		if (typeName.equals("boolean") || typeName.contains("Boolean")) {
			return TypeEnum.BOOLEANTYPE;
		}
		return TypeEnum.STRINGTYPE;
	}
	
	
	/**
	 * @describe: 类型枚举
	 * @author 唐兴甫
	 */
	enum TypeEnum {
		SIMPLETYPE, ARRAYTYPE, LISTTYPE, SETTYPE, MAPTYPE, OBJECTTYPE,
		NULLTYPE, BYTETYPE, SHORTTYPE, INTEGERTYPE, LONGTYPE, FLOATTYPE,
		DOUBLETYPE, CHARACTERTYPE, BOOLEANTYPE, DATETYPE, STRINGTYPE; 
	}
}