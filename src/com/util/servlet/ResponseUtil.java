package com.util.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import javax.servlet.http.HttpServletResponse;

/**
 * 响应工具类
 * @author 唐小甫
 * @datetime 2020-11-29 20:09:37
 */
public class ResponseUtil {
    
    
    /** 彻底得禁用缓冲，本地和代理服务器都不缓冲，每次都从服务器获取 */
    public static final String NO_STORE = "NO_STORE";
    /** 可以在本地缓存，可以在代理服务器缓存，但是这个缓存要服务器验证才可以使用 */
    public static final String NO_CACHE = "NO_CACHE ";
    
    /** UTF-8编码 */
    public static final String ENCODING_UTF8            = "UTF-8";
    /** GBK编码 */
    public static final String ENCODING_GBK             = "GBK";
    /** ISO-8859-1编码 */
    public static final String ENCODING_ISO_8859_1      = "ISO-8859-1";
    /** 字符编码UTF8 */
    public static final String CHARSET_UTF8             = ";CHARSET=UTF-8";
    /** 字符编码GBK */
    public static final String CHARSET_GBK              = ";CHARSET=GBK";
    /** JSON类型 */
    public static final String APPLICATION_JSON         = "APPLICATION/JSON";
    /** UTF8编码格式的JSON类型 */
    public static final String APPLICATION_JSON_UTF8    = "APPLICATION/JSON;CHAESET=UTF-8";
    
    
    /** 响应头缓存 */
    public static final String RESPONSE_HEADER_CACHE_CONTROLLER     = "CACHE-CONTROLLER";
    /** 响应头字段 */
    public static final String RESPONSE_HEADER_PRAGMA               = "PRAGMA";
    /** 响应头附件显示 */
    public static final String RESPONSE_HEADER_CONTENT_DISPOSITION  = "CONTENT-DISPOSITION";
    /** 响应头过期时间 */
    public static final String RESPONSE_DATE_HEADER_EXPIRES         = "EXPIRES";
    
    /** 附件下载 */
    public static final String DOWNLOAD_ATTACHMENT_FILENAME = "ATTACHMENT;FILENAME=";
    /** 附件在线 */
    public static final String INLINE_ATTACHMENT_FILENAME   = "INLINE;FILENAME=";
    

	/**
	 * 响应文本信息
	 * @param resp 响应对象
	 * @param json 响应文本
	 * @author 唐小甫
	 * @datetime 2020-11-26 22:52:40
	 */
	public static void writeContent(HttpServletResponse resp, String content) {
		resp.setContentType(APPLICATION_JSON_UTF8);
		resp.setCharacterEncoding(ENCODING_UTF8);
		try {
			Writer writer = resp.getWriter();
			writer.append(content);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
     * 下载文件
     * @param resp
     * @param file
     * @author 唐小甫
     * @datetime 2020-11-29 21:11:07
     * @see #responseFile(HttpServletResponse, File, String)
     */
    public static void download(HttpServletResponse resp, File file) {
        responseFile(resp, file, DOWNLOAD_ATTACHMENT_FILENAME);
    }
	
	
	/**
	 * 在线打开文件
	 * 注意：纯文本文件中汉字字符会出现乱码
	 * @param resp
	 * @param file
	 * @author 唐小甫
	 * @datetime 2020-11-29 21:11:07
	 * @see #responseFile(HttpServletResponse, File, String)
	 */
	public static void inline(HttpServletResponse resp, File file) {
		responseFile(resp, file, INLINE_ATTACHMENT_FILENAME);
	}
	

	/**
	 * 文件响应
	 * @param resp 响应对象
	 * @param file 待响应到客户端的文件
	 * @param cd   文件显示方式      在线预览-{@link #INLINE_ATTACHMENT_FILENAME},<br/>
	 *                         文件下载-{@link #DOWNLOAD_ATTACHMENT_FILENAME}
	 * @author 唐小甫
	 * @datetime 2020-11-29 20:41:01
	 */
	public static void responseFile(HttpServletResponse resp, File file, String cd) {
		try {
			String fileName = new String(file.getName().getBytes(ENCODING_GBK), ENCODING_ISO_8859_1);
			resp.setHeader(RESPONSE_HEADER_PRAGMA, NO_CACHE);
            resp.setHeader(RESPONSE_HEADER_CACHE_CONTROLLER, NO_CACHE);
            resp.setDateHeader(RESPONSE_DATE_HEADER_EXPIRES, 0);
			//设置响应头控制浏览器以下载的形式打开文件
			resp.setHeader(RESPONSE_HEADER_CONTENT_DISPOSITION, cd + fileName);
			String contentType = getContentType(file.getName());
            contentType += ContentType.TXT.getType().equals(contentType) ? CHARSET_GBK : CHARSET_UTF8;
            resp.setContentType(contentType);
			InputStream in = new FileInputStream(file);
			int count = 0;
			byte[] b = new byte[1024];
			OutputStream out = resp.getOutputStream();
			while((count = in.read(b)) != -1) {
				out.write(b, 0, count);//将缓冲区的数据输出到浏览器
			}
			in.close();
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * 获取文件响应类型
	 * @param fileName 文件名
	 * @return String  响应类型
	 * @author 唐小甫
	 * @datetime 2020-11-29 20:40:39
	 */
	public static String getContentType(String fileName){
		//文件后缀名
		String fileExtension = fileName.substring(fileName.lastIndexOf("."));
		String contentType = ContentType.getMap().get(fileExtension.toLowerCase());
		if (contentType == null || contentType.isEmpty()) {
		    return ContentType.getMap().get(".*");
		}
		return contentType;
	}
}