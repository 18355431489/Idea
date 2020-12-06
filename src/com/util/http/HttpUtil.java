package com.util.http;

import java.io.*;
import java.net.*;
import java.util.Map;

/**
 * HTTP工具类
 * @author 唐小甫
 * @datetime 2020-11-22 17:51:07
 */
public class HttpUtil {
    
    /** 可变长度字符编码 */
    public static final String ENCODING             = "UTF-8";
    /** 响应名称 */
    public static final String CONTENT_TYPE         = "Content-Type";
    /** 响应长度 */
    public static final String CONTENT_LENGTH       = "Content-Length";
    /** json请求格式 */
    public static final String APPLICATION_JSON     = "application/json";
    /** 默认请求格式 */
    public static final String APPLICATION_DEFAULT  = "application/x-www-form-urlencoded";
    /** GET请求 */
    public static final String METHOD_GET       = "GET";
    /** POST请求 */
    public static final String METHOD_POST      = "POST";
    /** PATCH请求 */
    public static final String METHOD_PATCH     = "PATCH";
    /** PUT请求 */
    public static final String METHOD_PUT       = "PUT";
    /** DELETE请求 */
    public static final String METHOD_DELETE    = "DELETE";
    /** HEAD请求 */
    public static final String METHOD_HEAD      = "HEAD";
    /** OPTIONS请求 */
    public static final String METHOD_OPTIONS   = "OPTIONS";
    /** TRACE请求 */
    public static final String METHOD_TRACE     = "TRACE";
    /** CONNECT请求 */
    public static final String METHOD_CONNECT   = "CONNECT";
    
    
    /**
     * 发送GET请求
     * @param path
     * @param params
     * @param encoding
     * @return java.lang.String
     * @throws IOException
     * @author 唐小甫
     * @datetime 2020-11-22 18:02:49
     */
    public static String sendGetRequest(String path, Map<String, Object> params, Map<String, Object> requestHeaderMap) throws IOException {
        StringBuilder url = new StringBuilder(path);
        url.append("?");
        if (params != null && !params.isEmpty()) {
            url.append(getParameterBuilder(params));
        }
        url.deleteCharAt(url.length() - 1);
        HttpURLConnection conn = (HttpURLConnection) new URL(url.toString()).openConnection();
        setRequestHeader(conn, requestHeaderMap);
        conn.setConnectTimeout(5000);
        conn.setRequestMethod(METHOD_GET);
        return getResponseText(conn);
    }


    /**
     * 发送POST请求
     * @param url      请求地址
     * @param params   请求参数
     * @param requestHeaderMap 请求头参数
     * @return java.lang.String
     * @throws IOException
     * @author 唐小甫
     * @datetime 2020-11-22 18:00:49
     */
    public static String sendPostRequest(String url, Map<String, Object> params, Map<String, Object> requestHeaderMap) throws IOException {
        StringBuilder data = new StringBuilder();
        if (params != null && !params.isEmpty()) {
            data = getParameterBuilder(params);
            data.deleteCharAt(data.length() - 1);
        }
        return sendPost(url, data.toString(), requestHeaderMap, APPLICATION_DEFAULT);
    }
    
    
    /**
     * 发送POST请求，传送json数据
     * @param url      请求地址
     * @param params   键值对
     * @param encoding 编码方式
     * @return java.lang.String
     * @throws IOException
     * @author 唐小甫
     * @datetime 2020-11-22 18:00:49
     */
    public static String sendPostJsonRequest(String url, String json, Map<String, Object> requestHeaderMap) throws IOException {
        return sendPost(url, json, requestHeaderMap, APPLICATION_JSON);
    }
    
    
    /**
     * 发送post请求
     * @param url
     * @param requestBody
     * @param requestHeaderMap
     * @param contentType
     * @return
     * @throws IOException String
     * @author 唐小甫
     * @datetime 2020-12-02 22:42:16
     */
    private static String sendPost(String url, String requestBody, Map<String, Object> requestHeaderMap, String contentType) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        setRequestHeader(conn, requestHeaderMap);
        conn.setConnectTimeout(5000);
        conn.setRequestMethod(METHOD_POST);
        //允许对外输出数据
        conn.setDoOutput(true);
        conn.setRequestProperty(CONTENT_TYPE, contentType);
        if (requestBody != null) {
            byte[] entity = requestBody.getBytes();
            conn.setRequestProperty(CONTENT_LENGTH, String.valueOf(entity.length));
            OutputStream outStream = conn.getOutputStream();
            outStream.write(entity);
        }
        return getResponseText(conn);
    }


    /**
     * 获取 key=value&key=value 形式的字符串
     * @param params   键值对
     * @return java.lang.StringBuilder
     * @throws UnsupportedEncodingException
     * @author 唐小甫
     * @datetime 2020-11-22 17:58:49
     */
    private static StringBuilder getParameterBuilder(Map<String, Object> params) throws UnsupportedEncodingException {
        StringBuilder data = new StringBuilder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            data.append(entry.getKey()).append("=");
            if(entry.getValue() != null) {
                String value = entry.getValue().toString();
                value = URLEncoder.encode(value, ENCODING);
                value = value.replaceAll("\\+", "%20");
                data.append(value);
            }
            data.append("&");
        }
        return data;
    }
    
    
    /**
     * 请求头参数
     * @param conn
     * @param requestHeaderMap
     * @throws UnsupportedEncodingException
     * @author 唐小甫
     * @datetime 2020-12-02 21:25:17
     */
    private static void setRequestHeader(URLConnection conn, Map<String, Object> requestHeaderMap) throws UnsupportedEncodingException {
        if (requestHeaderMap != null) {
            for (Map.Entry<String, Object> entry : requestHeaderMap.entrySet()) {
                if (entry.getValue() == null) {
                    conn.setRequestProperty(entry.getKey(), null);                    
                } else {
                    conn.setRequestProperty(entry.getKey(), URLEncoder.encode(entry.getValue().toString(), ENCODING));
                }
            }
        }
    }


    /**
     * 获取响应信息
     * @param conn
     * @return java.lang.String
     * @throws IOException
     * @author 唐小甫
     * @datetime 2020-11-22 17:55:49
     */
    private static String getResponseText(URLConnection conn) throws IOException {
        InputStream is = conn.getInputStream();
        int len;
        char[] b = new char[1024];
        String result = "";
        InputStreamReader isr = new InputStreamReader(is, ENCODING);
        while ((len = isr.read(b)) != -1) {
            result += new String(b, 0, len);
        }
        is.close();
        return result;
    }
}