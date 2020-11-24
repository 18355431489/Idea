package com.util.collection;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * 集合工具类
 * @author 唐小甫
 * @datetime 2020-11-21 14:57:02
 */
public class CollectionUtil {

    /**
     * 集合对象是否为空
     * @param collection
     * @return boolean
     * @author 唐小甫
     * @datetime 2020-11-21 15:42:49
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.size() == 0;
    }


    /**
     * 集合对象转字符串
     * @param collection 集合
     * @param separator  分隔符
     * @return java.lang.String
     * @author 唐小甫
     * @datetime 2020-11-21 14:59:49
     */
    public static String toCollectionFmt(Collection<String> collection, String separator) {
        if (isEmpty(collection)) {
            return null;
        }
        return collection.stream().collect(Collectors.joining(separator));
    }
}