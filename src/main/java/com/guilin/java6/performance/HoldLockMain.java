package com.guilin.java6.performance;

import java.util.Random;

/**
 * Created by guilin on 2016/9/20.
 * 线程切换频繁的程序
 * 在linux上通过vmstat工具查看程序cs(上下文切换)值很高，us(用户CPU时间)值也很高，表明程序的上下文切换频繁，用户CPU占用率高
 */
public class HoldLockMain {
    public static Object[] lock = new Object[10];
    public static Random r = new Random();

    static {
        for (int i = 0; i < lock.length; i++) {
            lock[i] = new Object();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < lock.length * 2; i++) {//每两个线程使用同一锁对象
            int a = i / 2;
            Thread thread = new Thread(new HoldLockTask(a));
            System.out.println("T " + thread.getName() + " " + a);
            thread.start();
        }
    }

    //一个持有锁的线程
    public static class HoldLockTask implements Runnable {

        private int i;

        public HoldLockTask(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    synchronized (lock[i]) {//持有锁
                        if (i % 2 == 0) {
                            System.out.println(Thread.currentThread().getName() + " wait " + i);
                            lock[i].wait(r.nextInt(10));//等待
                            System.out.println(Thread.currentThread().getName() + " wait finish " + i);
                        } else {
                            try {
                                Thread.sleep(r.nextInt(10) * 1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("notifyAll " + i);//通知
                            lock[i].notifyAll();
                        }
                    }
                }
            } catch (Exception e) {

            }
        }
    }
}
