package com.guilin.java6.nio;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

/**
 * Created by guilin on 2016/9/9.
 */
public class ByteBuffTest {

    //limit capacity position
    @Test
    public void test1() {
        ByteBuffer b = ByteBuffer.allocate(15);//15个字节大小的缓冲区
        System.out.println("limit:" + b.limit() + " capacity:" + b.capacity() + " position:" + b.position());//limit:15 capacity:15 position:0
        for (int i = 0; i < 10; i++) {//存入10个字节数据
            b.put((byte) i);
        }
        System.out.println("limit:" + b.limit() + " capacity:" + b.capacity() + " position:" + b.position());//limit:15 capacity:15 position:10

        b.flip();//重置position
        System.out.println("limit:" + b.limit() + " capacity:" + b.capacity() + " position:" + b.position());//limit:10 capacity:15 position:0
        for (int i = 0; i < 5; i++) {
            System.out.print(b.get());
        }
        System.out.println();//01234

        System.out.println("limit:" + b.limit() + " capacity:" + b.capacity() + " position:" + b.position());//limit:10 capacity:15 position:5
        b.flip();
        System.out.println("limit:" + b.limit() + " capacity:" + b.capacity() + " position:" + b.position());//limit:5 capacity:15 position:0
    }

    //create ByteBuffer
    @Test
    public void test2() {
        //从堆中分配
        ByteBuffer buffer = ByteBuffer.allocate(10);

        //从既有数组中创建
        byte[] b = new byte[10];
        ByteBuffer buffer2 = ByteBuffer.wrap(b);
    }

    @Test
    public void test3() {
        ByteBuffer buffer = ByteBuffer.allocate(20);
        for (int i = 0; i < 10; i++) {
            buffer.put((byte) i);
        }

        System.out.println("limit:" + buffer.limit() + " capacity:" + buffer.capacity() + " position:" + buffer.position());
//        buffer.rewind();//position置0，清除标志位(mark)，并不真正清空Buffer的内容

        buffer.clear();

        System.out.println("limit:" + buffer.limit() + " capacity:" + buffer.capacity() + " position:" + buffer.position());

        byte[] array = new byte[15];
        buffer.get(array);
        System.out.println(Arrays.toString(array));

        System.out.println("limit:" + buffer.limit() + " capacity:" + buffer.capacity() + " position:" + buffer.position());

    }

    //复制缓冲区
    @Test
    public void test4() {
        ByteBuffer b = ByteBuffer.allocate(15);
        for (int i = 0; i < 10; i++) {
            b.put((byte) i);
        }

        ByteBuffer c = b.duplicate();//复制当前缓冲区
        System.out.println("after b.duplicate()");
        System.out.println(b);
        System.out.println(c);

        c.flip();
        System.out.println("after c.flip()");
        System.out.println(b);
        System.out.println(c);

        c.put((byte) 100);
        System.out.println("after c.put((byte)100)");
        System.out.println("b.get(0)=" + b.get(0));
        System.out.println("c.get(0)=" + c.get(0));
        System.out.println(b);
        System.out.println(c);

        b.flip();
        while (b.hasRemaining()) {
            System.out.print(b.get());
        }
    }

    //缓冲区分片
    @Test
    public void test5() {
        ByteBuffer b = ByteBuffer.allocate(15);
        for (int i = 0; i < 10; i++) {
            b.put((byte) i);
        }

        b.position(2);
        b.limit(6);

        ByteBuffer subBuffer = b.slice();
        System.out.println(subBuffer);

        for (int i = 0; i < subBuffer.capacity(); i++) {//在子缓冲区中，将每个元素都*10
            byte bb = subBuffer.get(i);
            bb *= 10;
            subBuffer.put(i, bb);
        }

        b.position(0);
        b.limit(b.capacity());

        while (b.hasRemaining()) {
            System.out.print(b.get() + " ");
        }
    }

    //只读缓冲区
    @Test
    public void test6() {
        ByteBuffer b = ByteBuffer.allocate(15);
        for (int i = 0; i < 10; i++) {
            b.put((byte) i);
        }

        ByteBuffer readOnly = b.asReadOnlyBuffer();
        System.out.println(readOnly);
        readOnly.flip();
        while (readOnly.hasRemaining()) {
            System.out.print(readOnly.get() + " ");
        }
        System.out.println();

        b.put(2, (byte) 20);

        readOnly.flip();
        while (readOnly.hasRemaining()) {
            System.out.print(readOnly.get() + " ");
        }
    }

    //处理结构化数据
    //散射、聚集
    //通过ScatteringByteChannel和GatheringByteChannel，可以简化Buffer对结构化数据的处理，同时，有得于实现程序的模块化
    @Test
    public void test7() throws IOException {
        ByteBuffer bookBuf = ByteBuffer.wrap("java性能优化技巧".getBytes("utf-8"));
        ByteBuffer autBuf = ByteBuffer.wrap("张三".getBytes("utf-8"));

        int booklen = bookBuf.limit();
        int authlen = autBuf.limit();

        ByteBuffer[] bufs = new ByteBuffer[]{bookBuf, autBuf};
        String path = ByteBuffTest.class.getClassLoader().getResource("").getPath();
        File file = new File(path + "/test.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        FileOutputStream fos = new FileOutputStream(file);
        FileChannel fc = fos.getChannel();
        fc.write(bufs);//聚集写
        fos.close();

        ByteBuffer b1 = ByteBuffer.allocate(booklen);
        ByteBuffer b2 = ByteBuffer.allocate(authlen);
        ByteBuffer[] bufs2 = new ByteBuffer[]{b1, b2};
        FileInputStream fis = new FileInputStream(file);
        FileChannel fc2 = fis.getChannel();
        fc2.read(bufs2);//散射读
        String bookName = new String(bufs2[0].array(), "utf-8");
        String authName = new String(bufs2[1].array(), "utf-8");
        System.out.println(bookName + authName);
    }


}
