package com.guilin.java6.thread.concurrent;

import java.util.Date;

/**
 * Created by guilin1 on 16/7/6.
 * sleep和wait的区别
 * <p/>
 * wait方法是Object类的方法，这意味着所有的Java类都可以调用该方法。sleep方法是Thread类的静态方法。
 * wait是在当前线程持有wait对象锁的情况下，暂时放弃锁，并让出CPU资源，并积极等待其它线程调用同一对象的notify或者notifyAll方法。
 * 注意，即使只有一个线程在等待，并且有其它线程调用了notify或者notifyAll方法，等待的线程只是被激活，但是它必须得再次获得锁才能继续往下执行。
 * 换言之，即使notify被调用，但只要锁没有被释放，原等待线程因为未获得锁仍然无法继续执行
 * <p/>
 * sleep方法告诉操作系统至少指定时间内不需为线程调度器为该线程分配执行时间片，并不释放锁（如果当前已经持有锁）。实际上，调用sleep方法时并不要求持有任何锁。
 * sleep方法并不需要持有任何形式的锁，也就不需要包裹在synchronized中
 */
public class SleepAndWaitTest {

    public static void main(String[] args) {
//        waitTest();
        sleepTest();
    }

    public static void waitTest() {

        final String s = "s";

        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (s) {
                    try {
                        System.out.println(new Date() + "\tThread 1 is running");
//                        SleepAndWaitTest.class.wait();//synchronized后锁的对象应该与调用wait方法的对象一样,否则抛出IllegalMonitorStateException
                        s.wait();
                        System.out.println(new Date() + "\tThread 1 ended");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread1.start();


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (s) {
                    try {
                        System.out.println(new Date() + "\tThread 2 is running");

                        thread1.interrupt();
                        s.notify();
//                        SleepAndWaitTest.class.notify();
                        // Don't use sleep method to avoid confusing
                        for (long i = 0; i < 200000; i++) {
                            for (long j = 0; j < 100000; j++) {
                            }
                        }
                        System.out.println(new Date() + "\tThread 2 release lock");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    for (long i = 0; i < 200000; i++) {
                        for (long j = 0; j < 100000; j++) {
                        }
                    }

                    System.out.println(new Date() + "\tThread 2 ended");
                }
            }
        });

        // Don't use sleep method to avoid confusing
        for (long i = 0; i < 200000; i++) {
            for (long j = 0; j < 100000; j++) {
            }
        }
        thread2.start();
    }

    public static void sleepTest() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (SleepAndWaitTest.class) {
                    try {
                        System.out.println(new Date() + " Thread1 is running");
                        Thread.sleep(2000);
                        System.out.println(new Date() + " Thread1 ended");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        thread1.start();


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (SleepAndWaitTest.class) {
                    try {
                        System.out.println(new Date() + " Thread2 is running");
                        Thread.sleep(2000);
                        System.out.println(new Date() + " Thread2 ended");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                for (long i = 0; i < 200000; i++) {
                    for (long j = 0; j < 100000; j++) {
                    }
                }
            }
        });

        // Don't use sleep method to avoid confusing
        for (long i = 0; i < 200000; i++) {
            for (long j = 0; j < 100000; j++) {
            }
        }
        thread2.start();
    }

}
