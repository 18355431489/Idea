package com.util;

import okhttp3.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * http请求
 * @datetime: 2020/08/04 17:51:15
 * @author:tangxingfu
 */
public class AppTest {

    private OkHttpClient client = new OkHttpClient();

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
     *
     * @param baseUrl
     * @param params
     * @return
     * @throws IOException
     * @datetime: 2020/8/3 9:39
     * @author:tangxingfu
     */
    public String sendGet(String baseUrl, HashMap<String, Object> params) throws IOException {
        String url = baseUrl + "?";
        if (params != null && params.size() != 0) {
            for (Map.Entry entry : params.entrySet()) {
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
     *
     * @param baseUrl
     * @param params
     * @return
     * @throws IOException
     * @datetime: 2020/8/3 9:39
     * @author:tangxingfu
     */
    public String sendPost(String baseUrl, HashMap<String, Object> params) throws IOException {
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null && params.size() != 0) {
            for (Map.Entry entry : params.entrySet()) {
                if (entry != null && entry.getValue() != null) {
                    builder.add(entry.getKey().toString(), entry.getValue().toString());
                } else if (entry != null){
                    builder.add(entry.getKey().toString(), null);
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
     *
     * @param path
     * @param params
     * @param encoding
     * @return
     * @throws Exception
     * @datetime: 2020/8/3 9:39
     * @author:tangxingfu
     */
    public static String sendGETRequest(String path, Map<String, Object> params, String encoding) throws Exception {
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
     *
     * @param path
     * @param params
     * @param encoding
     * @return
     * @throws Exception
     * @datetime: 2020/8/3 9:39
     * @author:tangxingfu
     */
    public static String sendPOSTRequest(String path, Map<String, Object> params, String encoding) throws Exception {
        StringBuilder data = new StringBuilder();
        if (params != null && !params.isEmpty()) {
            data = getParameterBuilder(params, encoding);
            data.deleteCharAt(data.length() - 1);
        }
        System.out.println("POST请求地址: " + path);
        System.out.println("POST请求参数: " + data.toString());
        //生成实体数据
        byte[] entity = data.toString().getBytes();
        HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
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
     *
     * @datetime: 2020/8/4 11:13
     * @param params
     * @param encoding
     * @return:
     * @author:tangxingfu
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
     *
     * @datetime: 2020/8/3 10:00
     * @param conn
     * @return
     * @throws IOException
     * @author:tangxingfu
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