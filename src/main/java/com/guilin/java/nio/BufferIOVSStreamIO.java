package com.guilin.java.nio;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by guilin on 2016/9/8.
 * 速度MappedByteBuffer>ByteBuffer>Stream
 */
public class BufferIOVSStreamIO {

    private File file = new File(System.getProperty("java.io.tmpdir") + "test.txt");
    private int numOfInts = 4000000;

    @Test
    public void testStream() throws Exception {
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
        for (int i = 0; i < numOfInts; i++) {
            dos.writeInt(i);
        }
        if (dos != null) {
            dos.close();
        }
        DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
        for (int i = 0; i < numOfInts; i++) {
            dis.readInt();
        }
        if (dis != null) {
            dis.close();
        }
        file.deleteOnExit();
    }

    @Test
    public void testNio() throws Exception {
        FileOutputStream fout = new FileOutputStream(file);
        FileChannel fc = fout.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(numOfInts * 4);//分配Buffer
        for (int i = 0; i < numOfInts; i++) {
            byteBuffer.put(int2byte(i));
        }
        byteBuffer.flip();//准备写
        fc.write(byteBuffer);
        fc.close();

        FileInputStream fin = new FileInputStream(file);
        fc = fin.getChannel();
        byteBuffer = ByteBuffer.allocate(numOfInts * 4);
        fc.read(byteBuffer);
        fc.close();
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()) {
            byte2int(byteBuffer.get(), byteBuffer.get(), byteBuffer.get(), byteBuffer.get());
        }
        file.deleteOnExit();
    }

    @Test
    public void testMappedByteBuffer() throws Exception {
        FileChannel fc = new RandomAccessFile(file.getAbsolutePath(), "rw").getChannel();
        IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0, numOfInts * 4).asIntBuffer();//文件映射到内存
        for (int i = 0; i < numOfInts; i++) {
            ib.put(i);//写入文件
        }
        if (fc != null) {
            fc.close();
        }

        //读
        fc = new FileInputStream(file).getChannel();
        ib = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size()).asIntBuffer();
        while (ib.hasRemaining()) {
            ib.get();
        }
        fc.close();
        file.deleteOnExit();
    }

    public static byte[] int2byte(int res) {
        byte[] targets = new byte[4];
        targets[3] = (byte) (res & 0xff);//最低位
        targets[2] = (byte) ((res >> 8) & 0xff);//次低位
        targets[1] = (byte) ((res >> 16) & 0xff);//次高位
        targets[0] = (byte) (res >>> 24);//最高位，无符号右移
        return targets;
    }

    public static int byte2int(byte b1, byte b2, byte b3, byte b4) {
        return ((b1 & 0xff) << 24 | ((b2 & 0xff) << 16) | ((b3 & 0xff) << 8) | (b4 & 0xff));
    }

}
