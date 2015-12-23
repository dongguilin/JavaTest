package com.guilin.java6.thread;

/**
 * Created by hadoop on 2015/12/22.
 */
public class SynchronizedDemo {
    public synchronized static void foo1() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(5000);
        System.out.println("synchronized static void foo1");
    }

    public synchronized static void foo2() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(5000);
        System.out.println("synchronized static void foo2");
    }

    public synchronized void foo3() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(5000);
        System.out.println("synchronized void foo3");
    }

    public synchronized void foo4() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(5000);
        System.out.println("synchronized void foo4");
    }
}
