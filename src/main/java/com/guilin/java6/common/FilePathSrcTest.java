package com.guilin.java6.common;

import java.io.File;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: guilin
 * Date: 14-4-4
 * Time: 上午11:38
 * To change this template use File | Settings | File Templates.
 */
public class FilePathSrcTest {

    public static void main(String[] args) {
        /**
         * ""指项目根目录
         * "/"指项目所在盘根目录
         */
        File file1 = new File("hello.txt");//D:\IdeaProjects\MyTest\hello.txt
        File file2 = new File("/hello.txt"); //D:\hello.txt
        System.out.println("new File(\"hello.txt\")\t" + file1.getAbsolutePath());
        System.out.println("new File(\"/hello.txt\")\t" + file2.getAbsolutePath());

        File file3 = new File("Java6/res/hello.txt");
        System.out.println("Java6/res/hello.txt\t" + file3.getAbsolutePath());
        System.out.println(file3.exists());


        /**
         *   /D:/IdeaProjects/MyTest/JavaSE1.6/target/classes/code/csdn/net/zutuan5/javase6/common/
         *   /D:/IdeaProjects/MyTest/JavaSE1.6/target/classes/code/csdn/net/zutuan5/javase6/common/
         *   /D:/IdeaProjects/MyTest/JavaSE1.6/target/classes/
         *   /D:/IdeaProjects/MyTest/JavaSE1.6/target/classes/code/csdn/net/zutuan5/javase6/common/
         *   /D:/IdeaProjects/MyTest/JavaSE1.6/target/classes/code/csdn/net/zutuan5/javase6/
         *   /D:/IdeaProjects/MyTest/JavaSE1.6/target/classes/
         */
        URL url1 = FilePathSrcTest.class.getResource("");
        System.out.println(url1.getPath());
        URL url2 = FilePathSrcTest.class.getResource(".");
        System.out.println(url2.getPath());
        URL url3 = FilePathSrcTest.class.getResource("/");
        System.out.println(url3.getPath());
        URL url4 = FilePathSrcTest.class.getResource("./");
        System.out.println(url4.getPath());
        URL url5 = FilePathSrcTest.class.getResource("../");
        System.out.println(url5.getPath());
        URL url6 = FilePathSrcTest.class.getClassLoader().getResource("");
        System.out.println(url6.getPath());

        /**
         * true	D:\IdeaProjects\MyTest\JavaSE1.6\target\classes\FilePathSrcTest.txt
         */
        URL url7 = FilePathSrcTest.class.getResource("/FilePathSrcTest.txt");
        File urlF = new File(url7.getFile());
        System.out.println(urlF.exists() + "\t" + urlF.getAbsolutePath());

    }
}
