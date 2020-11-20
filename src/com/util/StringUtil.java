package com.util;

/**
 * @author: 唐小甫
 * @describe: 字符串工具
 * @createTime: 2020-05-14 14:52:11
 * @version: 1.0
 */
public class StringUtil {

    /**
     * @describe: 字符串是否为空
     * @param str
     * @return
     */
    public static Boolean isEmpty(String str) {
        if (str == null || str.trim().length() == 0) {
            return true;
        }
        return false;
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
	
	
	/**
	 * @describe: 通过文件后缀名获取响应类型
	 * @param fileName
	 * @return
	 */
	public static String getContentType(String fileName){
		//文件名后缀
		String fileExtension = fileName.substring(fileName.lastIndexOf("."));
		if(".bmp".equalsIgnoreCase(fileExtension)) {
			return "image/bmp";
		}
		if(".gif".equalsIgnoreCase(fileExtension)) {
			return "image/gif";
		}
		if(".jpeg".equalsIgnoreCase(fileExtension) || ".jpg".equalsIgnoreCase(fileExtension)  || ".png".equalsIgnoreCase(fileExtension) ){
			return "image/jpeg";
		}
		if(".html".equalsIgnoreCase(fileExtension)){
			return "text/html";
		}
		if(".txt".equalsIgnoreCase(fileExtension)){
			return "text/plain";
		}
		if(".vsd".equalsIgnoreCase(fileExtension)){
			return "application/vnd.visio";
		}
		if(".ppt".equalsIgnoreCase(fileExtension) || ".pptx".equalsIgnoreCase(fileExtension)) {
			return "application/vnd.ms-powerpoint";
		}
		if(".doc".equalsIgnoreCase(fileExtension) || ".docx".equalsIgnoreCase(fileExtension)) {
			return "application/msword";
		}
		if (".pdf".equalsIgnoreCase(fileExtension)) {
			return "application/pdf";
		}
		if(".xml".equalsIgnoreCase(fileExtension)) {
			return "text/xml";
		}
		return "image/jpeg";
	}
}