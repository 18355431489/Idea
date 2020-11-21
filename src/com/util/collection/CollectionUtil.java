package com.util.collection;

import java.util.*;

/**
 * 集合操作
 * @author 唐小甫
 * @create 2020/11/21 14:57
 */
public class CollectionUtil {

    /**
     * 集合对象是否为空
     * @param collection
     * @return boolean
     * @author 唐小甫
     * @datetime 2020/11/21 15:42
     */
    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.size() == 0;
    }


    /**
     * 集合对象转字符串
     * @param collection 集合
     * @param separator  分隔符
     * @return java.lang.String
     * @author 唐小甫
     * @datetime 2020/11/21 14:59
     */
    public static String toCollectionFmt(Collection collection, String separator) {
        StringBuilder strBuilder = new StringBuilder();
        if (isEmpty(collection)) {
            return strBuilder.toString();
        }
        for (Object obj : collection) {
            if (obj == null || obj.toString().length() == 0) {
                continue;
            }
            strBuilder.append(obj.toString() + separator);
        }
        return strBuilder.deleteCharAt(strBuilder.length() - 1).toString();
    }
}