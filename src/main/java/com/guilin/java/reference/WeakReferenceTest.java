package com.guilin.java.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Created by guilin on 2016/9/9.
 * 弱引用
 * 比软引用弱
 * 在GC时，只要发现弱引用，不管系统堆空间是否足够，都会将对象回收
 */
public class WeakReferenceTest {

    public static void main(String[] args) {
        MyObject obj = new MyObject();
        ReferenceQueue<MyObject> weakQueue = new ReferenceQueue<>();

        WeakReference<MyObject> weakRef = new WeakReference<MyObject>(obj, weakQueue);

        new CheckRefQueue(weakQueue).start();

        obj = null;

        System.out.println("Before GC:Weak Get= " + weakRef.get());

        System.gc();

        System.out.println("After GC:Weak Get= " + weakRef.get());

    }
}
