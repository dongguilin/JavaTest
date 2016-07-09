package com.guilin.java6.thread.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by T57 on 2016/7/8 0008.
 * 重入锁
 * 1.重入锁比内部锁synchronized功能更强大，可中断，可定时
 * 2.重入锁构造时可设置为公平锁或非公平锁，公平锁遵循先进先出，但是实现代价大，因此非公平锁性能更好
 * 3.lock()方法会一直等待，直到获取锁；lockInterruptibly()在等待期间可响应中断
 * tryLock(time,timeunit)会在指定的时间内等待锁，等待时间内可响应中断
 * tryLock()立即返回，不会进行等待
 */
public class ReentrantLockTest {

    private ReentrantLock lock = new ReentrantLock();//默认为非公平锁

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockTest test = new ReentrantLockTest();
        Thread first = new Thread(test.createTask(), "firstThread");
        Thread second = new Thread(test.createTask(), "secondThread");
        first.start();
        second.start();
        Thread.sleep(300);
        second.interrupt();
    }

    private Runnable createTask() {
        return new Runnable() {
            @Override
            public void run() {
                while (true) {
//                    try {
////                        if (lock.tryLock(500, TimeUnit.MICROSECONDS))
//                        if (lock.tryLock()) {
//                            try {
//                                System.out.println("locked " + Thread.currentThread().getName());
//                                Thread.sleep(1000);
//                            } finally {
//                                lock.unlock();
//                                System.out.println("unlocked " + Thread.currentThread().getName());
//                            }
//                            break;
//                        } else {
//                            System.out.println("unable to lock " + Thread.currentThread().getName());
//                        }
//                    } catch (InterruptedException e) {
//                        System.out.println(Thread.currentThread().getName() + " is Interrupted");
//                    }

                    try {
//                        lock.lock();
                        lock.lockInterruptibly();
                        try {
                            System.out.println("locked " + Thread.currentThread().getName());
                            Thread.sleep(1000);
                        } finally {
                            lock.unlock();
                            System.out.println("unlocked " + Thread.currentThread().getName());
                        }
                        break;

                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName() + " is Interrupted");
                    }
                }
            }
        };
    }
}
