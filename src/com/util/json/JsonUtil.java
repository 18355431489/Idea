package com.util.json;

import com.entity.Goods;
import com.entity.GoodsParent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json工具类
 * @author 唐小甫
 * @datetime 2020-11-22 21:26:06
 */
public class JsonUtil {

    /**
     * 对象转json字符串
     * @param <T>
     * @param t
     * @return String
     * @author 唐小甫
     * @datetime 2020-11-26 21:01:48
     */
    public static <T> String toJsonString(T t) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    
    /**
     * json字符串转对象
     * @param <T>
     * @param json
     * @param clazz 对象字节
     * @return T
     * @author 唐小甫
     * @datetime 2020-11-26 21:05:19
     */
    public static <T> T jsonString2Object(String json, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    /**
     * 对象类型转换
     * @param <T>
     * @param object
     * @param clazz
     * @return T
     * @author 唐小甫
     * @datetime 2020-11-26 21:07:17
     */
    public static <T> T objectTrans4(Object object, Class<T> clazz) {
        String json = toJsonString(object);
        return jsonString2Object(json, clazz);
    }
    
    
    public static void main(String[] args) {
//        Goods goods = new Goods();
//        goods.setName("正宗三鹿奶粉");
//        String json = toJsonString(goods);
////        System.out.println(jsonString2Object(json, Goods.class));
//        System.out.println(objectTrans4(goods, GoodsParent.class));
        GoodsParent goodsParent = new GoodsParent();
        System.out.println(goodsParent);
        System.out.println(toJsonString(goodsParent));
        System.out.println(objectTrans4(goodsParent, Goods.class));
    }
}