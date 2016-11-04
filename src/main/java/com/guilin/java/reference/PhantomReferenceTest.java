package com.guilin.java.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * Created by guilin on 2016/9/9.
 * 虚引用
 * 所有引用类型中最弱的一个，一个持有虚引用的对象，随时都可能被垃圾回收器回收，通过get()方法取得强引用时，总是会失败
 * 虚引用必须和引用队列一起使用，它的作用在于跟踪垃圾回收过程
 */
public class PhantomReferenceTest {

    public static void main(String[] args) throws InterruptedException {
        MyObject obj = new MyObject();
        ReferenceQueue<MyObject> phantomQueue = new ReferenceQueue<>();
        PhantomReference<MyObject> phantomRef = new PhantomReference<>(obj, phantomQueue);

        System.out.println("Phantom Get: " + phantomRef.get());

        new CheckRefQueue(phantomQueue).start();

        obj = null;

        Thread.sleep(1000);

        int i = 1;

        while (true) {
            System.out.println("第" + i++ + "次gc");
            System.gc();
            Thread.sleep(1000);
        }

        //在第一次GC时，系统找到了垃圾对象，并调用其finalize()方法回收内存，但没有立即加入回收队列，第二次GC时，该对象
        //真正被GC清除，此时，加入虚引用队列

    }
}
