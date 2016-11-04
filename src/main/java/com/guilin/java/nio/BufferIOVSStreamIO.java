package com.guilin.java.nio;

import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by guilin on 2016/9/8.
 */
public class BufferIOVSStreamIO {

    @Test
    public void testWriteStreamIO() throws Exception {
        String path = "E:/test/temp_stream.tmp";
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File(path))));
        int numOfInts = 4000000;//400万
        for (int i = 0; i < numOfInts; i++) {
            dos.writeInt(i);
        }
        if (dos != null) {
            dos.close();
        }
    }
}
