package com.util.qrcode;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.net.URL;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 二维码工具类
 * @author 唐小甫
 * @datetime 2020-12-12 23:29:53
 */
public class QRCodeUtil {
    
    /** 编码格式 */
    private static final String ENCODING        = "UTF-8";
    /** 图片后缀 */
    private static final String IMAGE_SUFFIX    = "JPG";
    /** 二维码尺寸 */
    private static final int QRCODE_SIZE        = 300;
    /** LOGO尺寸 */
    private static final int LOGO_SIZE          = QRCODE_SIZE / 5;
 
    
    /**
     * 生成二维码图片保存在磁盘中
     * @param content   内容
     * @param destPath  图片存放磁盘中的路径
     * @throws Exception
     * @author 唐小甫
     * @datetime 2020-12-12 23:43:10
     */
    public static void encode(String content, String destPath) throws Exception {
        QRCodeUtil.encode(content, null, destPath);
    }
    
 
    /**
     * 生成带有logo的二维码图片保存在磁盘中
     * @param content   内容
     * @param logoImage logo    
     * @param destPath  文件存放的全路径
     * @throws Exception
     * @author 唐小甫
     * @datetime 2020-12-12 23:31:09
     */
    public static void encode(String content, BufferedImage logoImage, 
            String destPath) throws Exception {
        QRCodeUtil.encode(content, logoImage, destPath, false);
    }
 
    
    /**
     * 生成二维码图片放在输出流中
     * @param content       内容
     * @param outputStream  输出流
     * @throws Exception
     * @author 唐小甫
     * @datetime 2020-12-12 23:42:17
     */
    public static void encode(String content, OutputStream outputStream) throws Exception {
        QRCodeUtil.encode(content, null, outputStream, false);
    }

 
    /**
     * 生成二维码图片对象放在输出流中
     * @param content       内容
     * @param logoImage     logo
     * @param needCompress  压缩：true/false
     * @return BufferedImage
     * @throws Exception
     * @author 唐小甫
     * @datetime 2020-12-12 23:30:38
     */
    public static BufferedImage encode(String content, BufferedImage logoImage, 
            boolean needCompress) throws Exception {
        return QRCodeUtil.createImage(content, logoImage, needCompress);
    }
    

    /**
     * 生成带有logo的二维码图片放在输出流中
     * @param content       内容
     * @param logoImage     logo
     * @param outputStream  输出流
     * @param needCompress  压缩：true/false
     * @throws Exception
     * @author 唐小甫
     * @datetime 2020-12-12 23:34:20
     */
    public static void encode(String content, BufferedImage logoImage, OutputStream outputStream, 
            boolean needCompress) throws Exception {
        BufferedImage image = QRCodeUtil.createImage(content, logoImage, needCompress);
        ImageIO.write(image, IMAGE_SUFFIX, outputStream);
    }
    
    
    
    /**
     * 生成二维码图片放在磁盘中
     * @param content       内容
     * @param logoImage     logo
     * @param destPath      文件存放的路径
     * @param needCompress  压缩：true/false
     * @throws Exception
     * @author 唐小甫
     * @datetime 2020-12-12 23:37:47
     */
    public static void encode(String content, BufferedImage logoImage, String destPath, 
            boolean needCompress) throws Exception {
        BufferedImage image = QRCodeUtil.createImage(content, logoImage, needCompress);
        File file = new File(destPath);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
        ImageIO.write(image, IMAGE_SUFFIX, new File(destPath));
    }
    
    
    /**
     * 生成二维码
     * @param content       内容
     * @param logoImage     logo
     * @param needCompress  压缩
     * @return BufferedImage
     * @throws Exception
     * @author 唐小甫
     * @datetime 2020-12-12 23:04:56
     */
    private static BufferedImage createImage(String content, BufferedImage logoImage
            , boolean needCompress) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, ENCODING);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, 
                BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        return logoImage == null ? image 
                : QRCodeUtil.insertImage(image, logoImage, needCompress);
    }
    
 
    /**
     * 图片中插入logo
     * @param sourceImage   原图
     * @param logoImage     logo
     * @param needCompress  压缩
     * @return BufferedImage
     * @throws Exception
     * @author 唐小甫
     * @datetime 2020-12-12 23:02:24
     */
    private static BufferedImage insertImage(BufferedImage sourceImage, Image logoImage, 
            boolean needCompress) throws Exception {
        int width = logoImage.getWidth(null);
        int height = logoImage.getHeight(null);
        if (needCompress) { // 压缩LOGO
            if (width > LOGO_SIZE) {
                width = LOGO_SIZE;
            }
            if (height > LOGO_SIZE) {
                height = LOGO_SIZE;
            }
            Image scaledImage = logoImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(scaledImage, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            logoImage = scaledImage;
        }
        // 插入LOGO
        Graphics2D graph = sourceImage.createGraphics();
        int x = (QRCODE_SIZE - width) / 2;
        int y = (QRCODE_SIZE - height) / 2;
        graph.drawImage(logoImage, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
        return sourceImage;
    }
    
    
    /**
     * 从url地址中读取二维码信息
     * @param imageUrl
     * @return String
     * @throws Exception
     * @author 唐小甫
     * @datetime 2020-12-12 22:39:51
     */
    public static String decode(URL imageUrl) throws Exception {
        return imageUrl == null ? null 
                : QRCodeUtil.decode(ImageIO.read(imageUrl));
    }
 
    
    /**
     * 从文件中读取二维码信息
     * @param file
     * @return String
     * @throws Exception
     * @author 唐小甫
     * @datetime 2020-12-12 22:40:16
     */
    public static String decode(File file) throws Exception {
        return file == null || !file.exists() ? null 
                : decode(ImageIO.read(file));
    }
    
    
    /**
     * 读取二维码信息
     * @param image 二维码图片缓存
     * @return String
     * @throws Exception
     * @author 唐小甫
     * @datetime 2020-12-12 22:34:38
     */
    public static String decode(BufferedImage image) throws Exception {
        if (image == null) {
            return null;
        }
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
        hints.put(DecodeHintType.CHARACTER_SET, ENCODING);
        return new MultiFormatReader().decode(bitmap, hints).getText();
    }
}