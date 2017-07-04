package com.guilin.java.thread.ab;

import java.util.concurrent.Semaphore;

/**
 * Created by guilin on 2017/7/4.
 * a、b两个线程交替执行
 * 使用公平信号量控制
 */
public class Semaphore1 {

    static Semaphore semaphore = new Semaphore(1, true);

    static class ThreadA extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    semaphore.acquire();
                    System.out.println("aaa");
                    semaphore.release();
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ThreadB extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    semaphore.acquire();
                    System.out.println("bbb");
                    semaphore.release();
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThreadA a = new ThreadA();
        ThreadB b = new ThreadB();
        a.start();
        b.start();
    }

}
