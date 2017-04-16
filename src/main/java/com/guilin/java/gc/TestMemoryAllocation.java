package com.guilin.java.gc;

import org.junit.Test;

import java.util.Vector;

/**
 * Created by guilin on 2017/3/28.
 */
public class TestMemoryAllocation {

    /**
     * 设置最大堆内存
     * 用-Xmx参数指定，最大堆指的是新生代和老年代的大小之和的最大值
     * 分别设置-Xmx8M、-Xmx14M测试
     */
    @Test
    public void testXmx() {
        System.out.println("Max memory:" + Runtime.getRuntime().maxMemory() / 1024 / 1024 + "M");//系统可用最大堆内存
        Vector v = new Vector();
        for (int i = 1; i <= 10; i++) {
            byte[] b = new byte[1024 * 1024];//每个循环分配1MB内存
            v.add(b);//强引用，使GC时不能释放空间
            System.out.println(i + "M is allocated");
        }
        System.out.println("Max memory:" + Runtime.getRuntime().maxMemory() / 1024 / 1024 + "M");
    }

    /**
     * 设置最小堆内存
     * 用-Xms参数指定
     * 分别设置-Xmx14M -Xms8M -verbose:gc
     * -Xmx14M -Xms14M -verbose:gc运行
     */
    @Test
    public void testXms() {
        System.out.println("Max memory:" + Runtime.getRuntime().maxMemory() / 1024 / 1024 + "M");//系统可用最大堆内存
        Vector v = new Vector();
        for (int i = 1; i <= 10; i++) {
            byte[] b = new byte[1024 * 1024];//分配1MB内存
            v.add(b);
            if (v.size() == 3) {
                v.clear();//清空内存
            }
        }

    }

}
