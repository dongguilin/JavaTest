package com.guilin.java.thread.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by T57 on 2016/7/9 0009.
 * 重入锁与读写锁性能测试
 */
public class ReentrantLockVSReadWriteLockTest {

    private static Lock lock = new ReentrantLock();

    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private static Lock readLock = readWriteLock.readLock();

    private static Lock writeLock = readWriteLock.writeLock();

    private static int value;

    public static void main(String[] args) throws InterruptedException {
        final ReentrantLockVSReadWriteLockTest test = new ReentrantLockVSReadWriteLockTest();
        long start = System.currentTimeMillis();

        final CountDownLatch countDownLatch = new CountDownLatch(3000);

        for (int i = 0; i < 2000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        test.handleRead();
//                        test.handleRead2();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    countDownLatch.countDown();
                }
            }).start();
        }
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        test.handleWrite(1);
//                        test.handleWrite2(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    countDownLatch.countDown();
                }
            }).start();
        }

        countDownLatch.await();
        System.out.println(System.currentTimeMillis() - start);
    }

    //使用重入锁同步读操作
    public Object handleRead() throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(10);//读操作的耗时越多，读写锁的优势越明显
            return value;
        } finally {
            lock.unlock();
        }
    }

    //使用重入锁同步写操作
    public void handleWrite(int index) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(10);
            value = index;
        } finally {
            lock.unlock();
        }
    }

    //使用读写锁同步读操作
    public Object handleRead2() throws InterruptedException {
        try {
            readLock.lock();
            Thread.sleep(10);//读操作的耗时越多，读写锁的优势越明显
            return value;
        } finally {
            readLock.unlock();
        }
    }

    //使用读写锁同步写操作
    public void handleWrite2(int index) throws InterruptedException {
        try {
            writeLock.lock();
            Thread.sleep(10);
            value = index;
        } finally {
            writeLock.unlock();
        }
    }

}
