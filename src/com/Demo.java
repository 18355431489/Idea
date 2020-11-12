package com;

import com.util.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @description:
 * @author: 唐小甫
 * @create: 2020/11/12 22:48
 * @version: 1.0
 */
public class Demo {
    public static void main(String[] args) throws IOException {
//        FileUtils.filesToZip("E:/Pictures", "D:/zip/压缩包.zip");
        File zipFile = new File("D:/zip/压缩包.zip");
        FileUtils.zipToFiles(zipFile, "D:/picture");
    }
}