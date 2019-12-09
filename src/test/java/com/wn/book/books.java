package com.wn.book;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2019/12/5.
 */
public class books {
    public static void main(String[] args) {


    }
    public void a1() throws IOException {
        File file = new File("G:\\xiaoshuo\\");
        System.out.println("名称：" + file.getName());
        System.out.println("是否存在：" + file.exists());
        if (!file.exists()) {
            // file.mkdir();//创建一级目录
            // file.mkdirs();//创建多级目录
            file.createNewFile();// 创建文件
        }

        System.out.println("文件是否存在" + file.isFile());// 是否存在文件
        System.out.println("目录是否存在" + file.isDirectory());// 是否存在目录
        System.out.println("文件地址：" + file.getAbsolutePath());
        System.out.println("文件名：" + file.getName());

        File[] fi = file.listFiles();
        for (File k : fi) {
            System.out.println(k.getName() + "---" + k.getAbsolutePath());
        }
        // file.delete();
    }

}
