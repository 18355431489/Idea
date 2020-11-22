package com.util.http;

import okhttp3.*;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

/**
 * HTTP工具类
 * @author 唐小甫
 * @createTime 2020-11-22 17:51:07
 */
public class HttpUtil {

    private OkHttpClient client = new OkHttpClient();

    /** 可变长度字符编码 */
    public static final String ENCODING = "UTF-8";

    /**
     * GET测试
     */
    public void test01() throws IOException {
        String base_url = "https://api.bilibili.com/x/web-show/res/locs";
        HashMap<String, Object> params = new HashMap<>(16);
        params.put("pf", "0");
        params.put("ids", "142,2837,2836,2870,2953,2954,2955,2956");
        System.out.println(sendGet(base_url, params));
    }

    /**
     * POST测试
     */
    public void test02() throws IOException {
        String url = "http://120.132.17.229:9687/JinCanFaceDataHelper.ashx";
        HashMap<String, Object> params = new HashMap<>(16);
        params.put("action", "hfjyrj");
        params.put("perCode", "210235C3MK3198000239");
        params.put("projectCode", "5d39b103-f4c8-4311-85c4-f3478ae980b9");
        params.put("record", "2020-08-04 10:00:00");
        params.put("atType", "0");
        params.put("idno", "340403196706172418");
        params.put("projectCodeId", "hfjyrj");
        System.out.println(sendPost(url, params));
    }


    /**
     * 发送GET请求
     * @param baseUrl
     * @param params
     * @return java.lang.String
     * @author 唐小甫
     * @datetime 2020-11-22 17:51:49
     */
    public String sendGet(String baseUrl, HashMap<String, Object> params) throws IOException {
        String url = baseUrl + "?";
        if (params != null && params.size() != 0) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                url += entry.getKey() + "=" + entry.getValue() + "&";
            }
        }
        url = url.substring(0, url.length() - 1);
        Request request = new Request.Builder().url(url).get().build();
        Call call = client.newCall(request);
        Response response = call.execute();
        return response.body().string();
    }


    /**
     * 发送POST请求
     * @param baseUrl
     * @param params
     * @return java.lang.String
     * @author 唐小甫
     * @datetime 2020-11-22 17:54:49
     */
    public String sendPost(String baseUrl, HashMap<String, Object> params) throws IOException {
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
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(baseUrl).post(requestBody).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }


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
    public static String sendGETRequest(String path, Map<String, Object> params, String encoding) throws IOException {
        StringBuilder url = new StringBuilder(path);
        url.append("?");
        if (params != null && !params.isEmpty()) {
            url.append(getParameterBuilder(params, encoding));
        }
        url.deleteCharAt(url.length() - 1);
        System.out.println("GET请求地址: " + url.toString());
        HttpURLConnection conn = (HttpURLConnection) new URL(url.toString()).openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        return getResponseText(conn);
    }



    /**
     * 发送POST请求
     * @param url      请求地址
     * @param params   键值对
     * @param encoding 编码方式
     * @return java.lang.String
     * @throws IOException
     * @author 唐小甫
     * @datetime 2020-11-22 18:00:49
     */
    public static String sendPOSTRequest(String url, Map<String, Object> params, String encoding) throws IOException {
        StringBuilder data = new StringBuilder();
        if (params != null && !params.isEmpty()) {
            data = getParameterBuilder(params, encoding);
            data.deleteCharAt(data.length() - 1);
        }
        System.out.println("POST请求地址: " + url);
        System.out.println("POST请求参数: " + data.toString());
        //生成实体数据
        byte[] entity = data.toString().getBytes();
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("POST");
        //允许对外输出数据
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(entity.length));
        OutputStream outStream = conn.getOutputStream();
        outStream.write(entity);
        return getResponseText(conn);
    }


    /**
     * 获取 key=value&key=value 形式的字符串
     * @param params   键值对
     * @param encoding 编码方式
     * @return java.lang.StringBuilder
     * @throws UnsupportedEncodingException
     * @author 唐小甫
     * @datetime 2020-11-22 17:58:49
     */
    private static StringBuilder getParameterBuilder(Map<String, Object> params, String encoding) throws UnsupportedEncodingException {
        StringBuilder data = new StringBuilder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            data.append(entry.getKey()).append("=");
            if(entry.getValue() != null) {
                data.append(URLEncoder.encode(entry.getValue().toString(), encoding));
            }
            data.append("&");
        }
        return data;
    }


    /**
     * 获取响应信息
     * @param conn
     * @return java.lang.String
     * @throws IOException
     * @author 唐小甫
     * @datetime 2020-11-22 17:55:49
     */
    private static String getResponseText(HttpURLConnection conn) throws IOException {
        InputStream is = conn.getInputStream();
        int len;
        char[] b = new char[1024];
        String result = "";
        InputStreamReader isr = new InputStreamReader(is, "UTF-8");
        while ((len = isr.read(b)) != -1) {
            result += new String(b, 0, len);
        }
        is.close();
        return result;
    }
}