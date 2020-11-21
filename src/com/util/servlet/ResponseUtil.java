package com.util.servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.net.URLEncoder;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

/**
 * @describe: 响应工具
 * @author 唐兴甫
 */
public class ResponseUtil {

	/**
	 * @describe: 响应JSON字符串
	 * @param resp
	 * @param json
	 */
	public static void writeJson(HttpServletResponse resp, String json) {
		resp.setContentType("APPLICATION/JSON; CHAESET=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try {
			Writer writer = resp.getWriter();
			writer.append(json);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * @describe: 响应图片
	 * @param resp
	 * @param image
	 */
	public static void writePicture(HttpServletResponse resp, BufferedImage image, String imageSuffix) {
		resp.setHeader("Cache-Controller", "no-store, no-cache");
		resp.setContentType("image/" + imageSuffix);
        try {
            ImageIO.write(image, imageSuffix, resp.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
	/**
	 * @describe: 响应多媒体
	 * @param resp
	 * @param file
	 */
	public static void writeMultiMedia(HttpServletResponse resp, File file) {
		try {
			String fileName = URLEncoder.encode(file.getName(), "UTF-8");
			resp.setHeader("Content-Disposition", "inline;fileName=" + fileName);
			resp.setContentType(getContentType(file.getName()) + ";charset=UTF-8");
			
			//设置响应头控制浏览器以下载的形式打开文件
			InputStream in = new FileInputStream(file);
			int count = 0;
			byte[] b = new byte[1024 * 10];
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
	 * @describe: 响应文件下载
	 * @param resp
	 * @param file
	 */
	public static void download(HttpServletResponse resp, File file) {
		try {
			String fileName = URLEncoder.encode(file.getName(), "UTF-8");
			//设置响应头控制浏览器以下载的形式打开文件
			resp.setHeader("content-disposition", "attachment;fileName=" + fileName);
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
	 * @describe: 通过文件后缀名获取响应类型
	 * @param fileName
	 * @return
	 */
	public static String getContentType(String fileName){
		//文件名后缀
		String fileExtension = fileName.substring(fileName.lastIndexOf("."));
		if(".bmp".equalsIgnoreCase(fileExtension)) {
			return "image/bmp";
		}
		if(".gif".equalsIgnoreCase(fileExtension)) {
			return "image/gif";
		}
		if(".jpeg".equalsIgnoreCase(fileExtension) || ".jpg".equalsIgnoreCase(fileExtension)  || ".png".equalsIgnoreCase(fileExtension) ){
			return "image/jpeg";
		}
		if(".html".equalsIgnoreCase(fileExtension)){
			return "text/html";
		}
		if(".txt".equalsIgnoreCase(fileExtension)){
			return "text/plain";
		}
		if(".vsd".equalsIgnoreCase(fileExtension)){
			return "application/vnd.visio";
		}
		if(".ppt".equalsIgnoreCase(fileExtension) || ".pptx".equalsIgnoreCase(fileExtension)) {
			return "application/vnd.ms-powerpoint";
		}
		if(".doc".equalsIgnoreCase(fileExtension) || ".docx".equalsIgnoreCase(fileExtension)) {
			return "application/msword";
		}
		if (".pdf".equalsIgnoreCase(fileExtension)) {
			return "application/pdf";
		}
		if(".xml".equalsIgnoreCase(fileExtension)) {
			return "text/xml";
		}
		return "image/jpeg";
	}
}