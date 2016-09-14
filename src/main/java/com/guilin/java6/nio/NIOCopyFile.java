package com.guilin.java6.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by guilin on 2016/9/9.
 * 使用NIO拷贝文件
 */
public class NIOCopyFile {

    public static void main(String[] args) throws IOException {
        String path = NIOCopyFile.class.getClassLoader().getResource("").getPath();
        nioCopyFile(path + "/left.jpg", path + "/left2.jpg");
    }

    public static void nioCopyFile(String resource, String destination) throws IOException {
        FileInputStream fis = new FileInputStream(resource);
        FileOutputStream fos = new FileOutputStream(destination);

        FileChannel readChannel = fis.getChannel();//读文件通道
        FileChannel writeChannel = fos.getChannel();//写文件通道

        ByteBuffer buffer = ByteBuffer.allocate(1024);//数据缓存
        while (true) {
            buffer.clear();
            int len = readChannel.read(buffer);//读入数据
            if (len == -1) {
                break;//读取完毕
            }
            buffer.flip();
            writeChannel.write(buffer);
        }
        readChannel.close();
        writeChannel.close();
    }
}
