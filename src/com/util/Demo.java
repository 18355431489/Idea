package com.util;

import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

import com.util.qrcode.QRCodeUtil;

/**
 * 测试类
 * 
 * @author 唐小甫
 * @datetime: 2020-12-06 12:02:52
 */
public class Demo {
    public static void main(String[] args) throws Exception {
        // 存放在二维码中的内容
        String text = "https://timgsa.baidu.com/timg?"
                + "image&quality=80&size=b9999_10000&sec=1607798359294"
                + "&di=f135d7d00ae529620834009e29c6d9b2&imgtype=0"
                + "&src=http%3A%2F%2Fa.hiphotos.baidu.com%2F"
                + "zhidao%2Fpic%2Fitem%2F91ef76c6a7efce1bed99ef79a451f3deb58f65d6.jpg";
        // 嵌入二维码的图片路径
//        String imgPath = "E:/Pictures/txf.jpg";
        // 生成的二维码的路径及名称
        String destPath = "E:/Pictures/qrcode1.jpg";
        // 生成二维码
        QRCodeUtil.encode(text, ImageIO.read(new URL(text)), destPath, true);
        String msg = QRCodeUtil.decode(new File(destPath));
        System.out.println(msg);
    }
}