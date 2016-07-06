package com.guilin.java6.thread.concurrent;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by guilin1 on 16/7/6.
 * 条件锁
 */
public class ConditionTest {

    public static void main(String[] args) {

        System.out.println(TimeUnit.SECONDS.toNanos(2));

        final Lock lock = new ReentrantLock();

        final Condition condition = lock.newCondition();

        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println(new Date() + "\tThread 1 is waiting");
                    try {
                        long waitTime = condition.awaitNanos(TimeUnit.SECONDS.toNanos(2));
                        System.out.println(new Date() + "\tThread 1 remaining time " + waitTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(new Date() + "\tThread 1 is waken up");
                } finally {
                    lock.unlock();
                    System.out.println(new Date() + "\tThread 1 unlock");
                }
            }
        });
        thread1.start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println(new Date() + "\tThread 2 is running");
                    try {
                        thread1.interrupt();
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    condition.signal();
                    System.out.println(new Date() + "\tThread 2 ended");
                } finally {
                    lock.unlock();
                    System.out.println(new Date() + "\tThread 2 unlock");

                }
            }
        }).start();

    }
}
