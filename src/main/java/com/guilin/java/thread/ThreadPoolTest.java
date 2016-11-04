package com.guilin.java.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by guilin on 2014/6/7.
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        //固定大小的线程池
//        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        //缓存线程池
//        ExecutorService threadPool = Executors.newCachedThreadPool();
        //单一线程池
        ExecutorService threadPool = Executors.newSingleThreadExecutor();

        for (int i = 1; i <= 10; i++) {
            final int task = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    for (int j = 1; j <= 10; j++) {
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + " is looping for " + j + " for task of " + task);
                    }
                }
            });
        }
        System.out.println("all of 10 tasks have commited!");
        threadPool.shutdownNow();

        Executors.newScheduledThreadPool(3).schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("bombing!");
            }
        }, 10, TimeUnit.SECONDS);

        Executors.newScheduledThreadPool(3).scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("bombing!");
            }
        }, 6, 2, TimeUnit.SECONDS);

        Executors.newScheduledThreadPool(3).scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("bombing!");
            }
        }, 6, 2, TimeUnit.SECONDS);

    }

}
