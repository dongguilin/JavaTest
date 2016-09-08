package com.guilin.java6.performance;

import org.junit.Test;

import java.io.*;

/**
 * Created by guilin on 2016/9/8.
 */
public class IOTest {

    //FileReader和FileWriter的性能要优于直接使用FileInputStream和FileOutputStream

    //208ms
    @Test
    public void test1() throws IOException {
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("testfile.txt")));
        for (int i = 0; i < 1000000; i++) {
            dos.writeBytes(String.valueOf(i) + "\r\n");
        }
        dos.close();
    }

    //83ms
    @Test
    public void test2() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("testfile.txt"));
        for (int i = 0; i < 1000000; i++) {
            writer.write(String.valueOf(i) + "\r\n");
        }
        writer.close();
    }
}
