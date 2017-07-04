package com.guilin.java.thread.ab;

/**
 * Created by guilin on 2017/7/4.
 * a、b两个线程交替执行
 * 使用同步互斥锁+状态码控制
 */
public class Sync1 {

    static Object lock = new Object();
    static boolean isA = true;

    static class ThreadA extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    if (isA) {
                        System.out.println("aaa");
                        isA = false;
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
    }

    static class ThreadB extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    if (!isA) {
                        System.out.println("bbb");
                        isA = true;
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadA a = new ThreadA();
        a.start();
        ThreadB b = new ThreadB();
        b.start();
    }

}
