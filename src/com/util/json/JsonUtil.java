package com.util.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
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
        // 获取成员值时设置可见性
        objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        objectMapper.setVisibility(PropertyAccessor.GETTER, Visibility.ANY);
        objectMapper.setVisibility(PropertyAccessor.IS_GETTER, Visibility.ANY);
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
        // JSON工厂对象设置属性兼容单引号
        JsonFactory jsonFactory = new JsonFactory();
        jsonFactory.enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);
        ObjectMapper objectMapper = new ObjectMapper(jsonFactory);
        // JSON多余属性不做处理
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
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
}