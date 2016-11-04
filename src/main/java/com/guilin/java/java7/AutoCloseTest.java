package com.guilin.java.java7;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 动态资源管理
 *
 * @author Administrator
 */
public class AutoCloseTest {

    /**
     * Java7之前需要在finally中关闭socket、文件、数据库连接等资源；
     * Java7中在try语句中申请资源，实现资源的自动释放（资源类必须实现java
     * .lang.AutoCloseable接口，一般的文件、数据库连接等均已实现该接口，close方法将被自动调用）
     *
     * @throws java.io.IOException
     */
    @Test
    public void testAutoClose() {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("d:/config.xml"));
            StringBuilder builder = new StringBuilder();
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                builder.append(str);
                builder.append(String.format("%n"));
            }
//			System.out.println(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(bufferedReader.read());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    {
        System.out.println("b");
    }

    static {
        System.out.println("a");
    }

}
