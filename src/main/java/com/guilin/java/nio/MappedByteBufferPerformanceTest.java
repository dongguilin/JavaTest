package com.guilin.java.nio;

import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by guilin on 2016/9/9.
 */
public class MappedByteBufferPerformanceTest {

    private File file = new File("E:/test/test.txt");
    private int numOfInts = 4000000;

    @Test
    public void testStreamWrite() throws Exception {
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
        for (int i = 0; i < numOfInts; i++) {

        }
    }
}
