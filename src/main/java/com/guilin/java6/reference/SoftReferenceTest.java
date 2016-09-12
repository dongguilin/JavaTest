package com.guilin.java6.reference;

import org.junit.Test;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * Created by guilin on 2016/9/8.
 * 软引用
 * 软引用是除了强引用外，最强的引用类型
 * 一个持有软引用的对象，不会被JVM很快回收，JVM会根据当前堆的使用情况来判断何时回收。当堆使用率临近阈值时，才会去回收
 * 软引用的对象，只要有足够的内存，软引用便可能在内存中存活相当长的一段时间。
 * 软引用可以用于实现对内存敏感的Cache
 */
public class SoftReferenceTest {

    public static void main(String[] args) {
        MyObject obj = new MyObject();//强引用
        ReferenceQueue<MyObject> softQueue = new ReferenceQueue<>();//引用队列

        //使用SoftReference构造obj对象的软引用softRef，并注册到softQueue引用队列
        SoftReference<MyObject> softRef = new SoftReference<MyObject>(obj, softQueue);

        //检查引用队列，监控对象回收情况
        CheckRefQueue checkRefQueue = new CheckRefQueue(softQueue);
        checkRefQueue.start();

        //删除强引用
        obj = null;

        System.gc();

        System.out.println("After GC:Soft Get= " + softRef.get());

        //系统内存紧张时，软引用会被回收
        System.out.println("分配大块内存");
        byte[] b = new byte[1000 * 1024 * 1525];//分配一块较大的内存，强迫GC
        System.out.println("After new byte[]:Soft Get= " + softRef.get());

    }

    @Test
    public void test1() {

    }
}
