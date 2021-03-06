package com.util.http;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * OkHttp工具类
 * @author 唐小甫
 * @datetime: 2020-12-03 22:04:10
 */
public class OkHttpUtil {

    private static OkHttpClient client = new OkHttpClient();

    /** 可变长度字符编码 */
    public static final String ENCODING = "UTF-8";

    public static final String MEDIA_TYPE_JSON = "APPLICATION/JSON; CHARSET=UTF-8";


    /**
     * 发送GET请求
     * @param baseUrl
     * @param params
     * @param requestHeader
     * @return java.lang.String
     * @author 唐小甫
     * @datetime 2020-11-22 17:51:49
     */
    public static String sendGet(String baseUrl, Map<String, Object> params, Map<String, String> requestHeader) throws IOException {
        String url = baseUrl + "?";
        if (params != null && params.size() != 0) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                url += entry.getKey() + "=" + entry.getValue() + "&";
            }
        }
        url = url.substring(0, url.length() - 1);
        Builder reqBuilder = new Request.Builder();
        if (requestHeader != null) {
            for (Entry<String, String> entry : requestHeader.entrySet()) {
                reqBuilder = reqBuilder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        Request request = reqBuilder.url(url).get().build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }


    /**
     * 发送表单请求
     * @param baseUrl
     * @param params
     * @param requestHeader
     * @param method
     * @return java.lang.String
     * @author 唐小甫
     * @datetime 2020-11-22 17:54:49
     */
    public static String sendForm(String baseUrl, Map<String, Object> params, Map<String, String> requestHeader, String method) throws IOException {
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null && params.size() != 0) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                if (entry != null && entry.getValue() != null) {
                    builder.add(entry.getKey(), entry.getValue().toString());
                } else if (entry != null){
                    builder.add(entry.getKey(), null);
                }
            }
        }
        RequestBody requestBody = builder.build();
        Builder reqBuilder = new Request.Builder();
        if (requestHeader != null) {
            requestHeader.entrySet().forEach(entry -> 
                reqBuilder.addHeader(entry.getKey(), entry.getValue()));
        }
        Request request = reqBuilder.url(baseUrl).method(method, requestBody).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    
    
    /**
     * 发送JSON请求
     * @param baseUrl
     * @param json
     * @param requestHeader
     * @param method
     * @return java.lang.String
     * @author 唐小甫
     * @datetime 2020-11-22 17:54:49
     */
    public static String sendJson(String baseUrl, String json, Map<String, String> requestHeader, String method) throws IOException {
        Builder reqBuilder = new Request.Builder();
        if (requestHeader != null) {
            requestHeader.entrySet().forEach(entry -> 
                reqBuilder.addHeader(entry.getKey(), entry.getValue()));
        }
        MediaType JSON = MediaType.parse(MEDIA_TYPE_JSON);
        RequestBody requestBody = RequestBody.create(json, JSON);
        Request request = reqBuilder.url(baseUrl).method(method, requestBody).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}