package com.guilin.java.nio;

import org.junit.Test;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by guilin on 2017/3/27.
 * DirectBuffer的读写操作比普通Buffer快，但是它的创建和销毁却比普通Buffer慢
 */
public class TestDirectBuffer {

    @Test
    public void testMonDirectBuffer() throws Exception {
        Class c = Class.forName("java.nio.Bits");
        Field maxMemory = c.getDeclaredField("maxMemory");
        maxMemory.setAccessible(true);
        Field reservedMemory = c.getDeclaredField("reservedMemory");
        reservedMemory.setAccessible(true);
        Field totalCapacity = c.getDeclaredField("totalCapacity");
        totalCapacity.setAccessible(true);
        synchronized (c) {
            Long maxMemoryValue = (Long) maxMemory.get(null);//总大小
            AtomicLong reservedMemoryValue = (AtomicLong) reservedMemory.get(null);//剩余大小
            AtomicLong totalCapacityValue = (AtomicLong) totalCapacity.get(null);
            System.out.println("maxMemoryValue:" + maxMemoryValue);
            System.out.println("reservedMemoryValue:" + reservedMemoryValue);
            System.out.println("totalCapacityValue:" + totalCapacityValue);
        }

    }

    @Test
    public void testDirectBuffer() {
        ByteBuffer b = ByteBuffer.allocateDirect(500);//分配DirectBuffer
        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < 99; j++) {
                b.putInt(j);
            }
            b.flip();
            for (int j = 0; j < 99; j++) {
                b.getInt();
            }
            b.clear();
        }
    }

    @Test
    public void testByteBuffer() {
        ByteBuffer b = ByteBuffer.allocate(500);//分配heap buffer
        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < 99; j++) {
                b.putInt(j);
            }
            b.flip();
            for (int j = 0; j < 99; j++) {
                b.getInt();
            }
            b.clear();
        }
    }

    //直接在内存中分配Buffer
    //-XX:MaxDirectMemorySize=10M -Xmx10M
    @Test
    public void testCreateDirectBuffer() {
        for (int i = 0; i < 200000; i++) {
            ByteBuffer b = ByteBuffer.allocateDirect(1000);
        }
    }

    //在堆上分配Buffer
    //-XX:MaxDirectMemorySize=10M -Xmx10M
    @Test
    public void testCreateByteBuffer() {
        for (int i = 0; i < 200000; i++) {
            ByteBuffer b = ByteBuffer.allocate(1000);
        }
    }
}
