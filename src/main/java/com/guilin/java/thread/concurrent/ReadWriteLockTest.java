package com.guilin.java.thread.concurrent;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by guilin1 on 16/7/6.
 * 读写锁
 * <p/>
 * 对于读多写少的场景，一个读操作无须阻塞其它读操作，只需要保证读和写或者写与写不同时发生即可
 * 获得读锁后，其它线程可获得读锁而不能获取写锁
 * 获得写锁后，其它线程即不能获得读锁也不能获得写锁
 */
public class ReadWriteLockTest {

    public static void main(String[] args) {

        final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                readWriteLock.readLock().lock();

                try {
                    System.out.println(new Date() + "\tThread 1 started with read lock");
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    System.out.println(new Date() + "\tThread 1 ended");
                } finally {
                    readWriteLock.readLock().unlock();
                }
            }
        });
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                readWriteLock.readLock().lock();

                try {
                    System.out.println(new Date() + "\tThread 2 started with read lock");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    System.out.println(new Date() + "\tThread 2 ended");
                } finally {
                    readWriteLock.readLock().unlock();
                }
            }
        });
        thread2.start();

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                Lock lock = readWriteLock.writeLock();
                lock.lock();

                try {
                    System.out.println(new Date() + "\tThread 3 started with write lock");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    System.out.println(new Date() + "\tThread 3 ended");
                } finally {
                    lock.unlock();
                }
            }
        });
        thread3.start();

    }
}
