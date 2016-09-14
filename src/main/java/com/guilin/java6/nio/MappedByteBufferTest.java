package com.guilin.java6.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by guilin on 2016/9/9.
 */
public class MappedByteBufferTest {

    public static void main(String[] args) throws Exception {
        RandomAccessFile raf = new RandomAccessFile("E:/test/FilePathSrcTest.txt", "rw");
        FileChannel fileChannel = raf.getChannel();

        //文件映射到内存
        MappedByteBuffer mbb = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, raf.length());
        while (mbb.hasRemaining()) {
            System.out.print((char) mbb.get());
        }

        mbb.put(0, (byte) 98);//修改文件

        raf.close();

    }
}
