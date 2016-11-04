package com.guilin.java6.performance;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;

/**
 * Created by guilin on 2016/9/8.
 */
public class IOTest {

    //FileReader和FileWriter的性能要优于直接使用FileInputStream和FileOutputStream

    private String basePath = IOTest.class.getClassLoader().getResource(".").getPath();

    @Test
    public void test1() throws IOException {
        long start = System.currentTimeMillis();
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream
                (new FileOutputStream(basePath + File.separator + "DataOutputStream.tmp")));
        for (int i = 0; i < 1000000; i++) {
            dos.writeBytes(String.valueOf(i) + "\r\n");
        }
        dos.close();
        long end = System.currentTimeMillis();
        long took1 = end - start;
        System.out.println(took1);

        start = System.currentTimeMillis();
        BufferedWriter writer = new BufferedWriter(new FileWriter(basePath + File.separator + "BufferedWriter.tmp"));
        for (int i = 0; i < 1000000; i++) {
            writer.write(String.valueOf(i) + "\r\n");
        }
        writer.close();
        end = System.currentTimeMillis();
        long took2 = end - start;
        System.out.println(took2);

        Assert.assertTrue(took2 <= took1);
    }

    @Test
    public void test2() throws Exception {
        long start = System.currentTimeMillis();
        FileOutputStream fos = new FileOutputStream(basePath + File.separator + "FileOutputStream.tmp");
        for (int i = 0; i < 1000000; i++) {
            fos.write((String.valueOf(i) + "\r\n").getBytes());
        }
        fos.close();
        long end = System.currentTimeMillis();
        long took1 = end - start;
        System.out.println(took1);

        start = System.currentTimeMillis();
        FileWriter fileWriter = new FileWriter(basePath + File.separator + "FileWriter.tmp");
        for (int i = 0; i < 1000000; i++) {
            fileWriter.write(String.valueOf(i) + "\r\n");
        }
        fileWriter.close();
        end = System.currentTimeMillis();
        long took2 = end - start;
        System.out.println(took2);

        Assert.assertTrue(took2 <= took1);
    }
}
