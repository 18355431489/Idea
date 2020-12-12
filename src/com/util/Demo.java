package com.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.util.http.OkHttpUtil;
import com.util.json.JsonUtil;

/**
 * 测试类
 * 
 * @author 唐小甫
 * @datetime: 2020-12-06 12:02:52
 */
public class Demo {
    public static void main(String[] args) throws IOException {
        String baseUrl = "https://img-home.csdnimg.cn/data_json/toolbar/toolbar.json";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("1", null);
        String json = OkHttpUtil.sendGet(baseUrl, params, null);
        System.out.println(json);
        @SuppressWarnings("unchecked")
        Map<String, Object> map = JsonUtil.json2Object(json, Map.class);
        System.out.println(map);
    }
}