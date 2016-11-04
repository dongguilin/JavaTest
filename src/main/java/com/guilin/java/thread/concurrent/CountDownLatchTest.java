package com.guilin.java.thread.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * Created by T57 on 2016/9/6 0006.
 * 闭锁
 */
public class CountDownLatchTest {

    static class MyThread implements Runnable {

        @Override
        public void run() {
            System.out.println("hello " + Thread.currentThread().getName());
        }
    }

    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);//启动门
        final CountDownLatch endGate = new CountDownLatch(nThreads);//结束门

        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            endGate.countDown();
                        }
                    } catch (InterruptedException e) {
                    }
                }
            };
            t.start();
        }

        long start = System.currentTimeMillis();
        startGate.countDown();
        endGate.await();
        long end = System.currentTimeMillis();
        return end - start;
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatchTest test = new CountDownLatchTest();
        MyThread thread = new MyThread();
        long time = test.timeTasks(5, thread);
        System.out.println(time);
    }


}
