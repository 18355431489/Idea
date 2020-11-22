package com.util.file;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * 文件工具类
 * @author 唐小甫
 * @createTime 2020-11-22 17:44:37
 */
public class FileUtil {

    /**
     * 获取文件、文件夹中所有的文件对象
     *
     * @param path 路径
     * @return java.util.List<java.io.File>
     * @author 唐小甫
     * @datetime 2020-11-11 10:11:49
     */
    public static List<File> getFilesByPath(String path) {
        List<File> files = new ArrayList<>();
        File file = new File(path);
        if (!file.exists()) {
            return files;
        }
        if (file.isFile()) {
            files.add(file);
            return files;
        }
        file.listFiles(childFile -> {
            if (childFile.isFile()) {
                files.add(childFile);
                return true;
            }
            files.addAll(getFilesByPath(childFile.getPath()));
            return false;
        });
        return files;
    }


    /**
     * 递归删除文件/文件夹
     *
     * @param filePath 文件路径
     * @return void
     * @author 唐小甫
     * @datetime 2020-11-11 10:54:49
     */
    public static void deleteFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }
        if (file.isFile()) {
            file.delete();
            return;
        }
        File[] files = file.listFiles();
        for (File childFile : files) {
            deleteFile(childFile.getPath());
        }
    }


    /**
     * 文件压缩（不包含目录）
     *
     * @param files    文件集合
     * @param filePath 压缩文件存放路径
     * @param zipName  压缩文件名
     * @return java.io.File
     * @author 唐小甫
     * @datetime 2020-11-11 9:27:49
     */
    public static File filesToZipExcludeDirectory(List<File> files, String filePath, String zipName) {
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String realPath = filePath + "/" + zipName;
        File zipFile = new File(realPath);
        // 判断压缩后的文件存在不，不存在则创建
        if (!zipFile.exists()) {
            try {
                zipFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 创建 FileOutputStream 对象
        FileOutputStream fileOutputStream = null;
        // 创建 ZipOutputStream
        ZipOutputStream zipOutputStream = null;
        // 创建 FileInputStream 对象
        FileInputStream fileInputStream = null;

        try {
            // 实例化 FileOutputStream 对象
            fileOutputStream = new FileOutputStream(zipFile);
            // 实例化 ZipOutputStream 对象
            zipOutputStream = new ZipOutputStream(fileOutputStream);
            // 创建 ZipEntry 对象
            ZipEntry zipEntry = null;
            // 遍历源文件数组
            for (int i = 0; i < files.size(); i++) {
                // 将源文件数组中的当前文件读入 FileInputStream 流中
                fileInputStream = new FileInputStream(files.get(i));
                // 实例化 ZipEntry 对象，源文件数组中的当前文件
                zipEntry = new ZipEntry(files.get(i).getName());
                zipOutputStream.putNextEntry(zipEntry);
                // 该变量记录每次真正读的字节个数
                int len;
                // 定义每次读取的字节数组
                byte[] buffer = new byte[1024];
                while ((len = fileInputStream.read(buffer)) > 0) {
                    zipOutputStream.write(buffer, 0, len);
                }
            }
            zipOutputStream.closeEntry();
            zipOutputStream.close();
            fileInputStream.close();
            fileOutputStream.close();
            return zipFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 文件压缩
     *
     * @param sourcePath 文件或文件夹路径
     * @param zipPath    zip文件的全路径
     * @return void
     * @author 唐小甫
     * @datetime 2020-11-22 17:48:49
     */
    public static void filesToZip(String sourcePath, String zipPath) {
        FileOutputStream fout = null;
        ZipOutputStream zipOut = null;
        try {
            File directory = new File(zipPath.substring(0, zipPath.lastIndexOf("/")));
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File zipFile = new File(zipPath);
            // 判断压缩后的文件存在不，不存在则创建
            if (!zipFile.exists()) {
                try {
                    zipFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            fout = new FileOutputStream(zipFile);
            zipOut = new ZipOutputStream(fout);
            writeZip(new File(sourcePath), "", zipOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (zipOut != null) {
                    zipOut.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 写入压缩文件
     *
     * @param file       文件
     * @param parentPath 父路径
     * @param zipOut     压缩输出流
     * @return void
     * @author 唐小甫
     * @datetime 2020-11-11 15:33:49
     */
    private static void writeZip(File file, String parentPath, ZipOutputStream zipOut) {
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            //处理文件夹
            parentPath += file.getName() + File.separator;
            File[] files = file.listFiles();
            if (files.length != 0) {
                for (File childFile : files) {
                    writeZip(childFile, parentPath, zipOut);
                }
            } else {
                //空目录则创建当前目录
                try {
                    zipOut.putNextEntry(new ZipEntry(parentPath));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            FileInputStream fin = null;
            try {
                fin = new FileInputStream(file);
                ZipEntry entry = new ZipEntry(parentPath + file.getName());
                zipOut.putNextEntry(entry);
                byte[] content = new byte[1024];
                int len;
                while ((len = fin.read(content)) != -1) {
                    zipOut.write(content, 0, len);
                    zipOut.flush();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fin != null) {
                        fin.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 文件解压缩
     *
     * @param zipFile 压缩文件
     * @param descDir 解压文件存放路径
     * @return java.util.List<java.io.File>
     * @throws IOException
     * @author 唐小甫
     * @datetime 2020-11-11 9:29:49
     */
    public static List<File> zipToFiles(File zipFile, String descDir) throws IOException {
        List<File> list = new ArrayList<>();
        File pathFile = new File(descDir);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        } else {
            deleteFile(descDir);
        }
        ZipFile zip = new ZipFile(zipFile);
        for (Enumeration<?> entries = zip.entries(); entries.hasMoreElements(); ) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            String zipEntryName = entry.getName();
            InputStream in = zip.getInputStream(entry);
            String outPath = (descDir + "/" + zipEntryName).replaceAll("\\\\", "/");
            //判断路径是否存在,不存在则创建文件路径
            File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
            if (!file.exists()) {
                file.mkdirs();
            }
            //输出文件路径信息
            list.add(file);
            System.out.println(outPath);
            //判断文件全路径是否为文件夹,不需要解压
            if (new File(outPath).isDirectory()) {
                continue;
            }
            File outFile = new File(outPath);
            if (outFile.exists()) {
                outFile.delete();
            }
            outFile.createNewFile();
            OutputStream out = new FileOutputStream(outPath);
            byte[] buf1 = new byte[1024];
            int len;
            while ((len = in.read(buf1)) > 0) {
                out.write(buf1, 0, len);
            }
            in.close();
            out.close();
        }
        zip.close();
        System.out.println("******************解压完毕********************");
        return list;
    }
}