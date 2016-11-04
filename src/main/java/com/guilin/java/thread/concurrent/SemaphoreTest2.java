package com.guilin.java.thread.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by hadoop on 2015/12/26.
 * 信号量
 * 10个资源，可用时提供5个，没有可用时阻塞等待，用完时，释放资源(release)
 */
public class SemaphoreTest2 {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        //获取信号许可，成功后，semaphore可用信号量就会减少一个，如是没有可用信号，acquire调用就会阻塞，等待
                        //有release调用释放信号后，acquire才会得到信号并返回
                        semaphore.acquire();
                        System.out.println("Accessing:" + finalI);
                        Thread.sleep((long) (Math.random() * 6000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        //归还信号许可
                        semaphore.release();
                        System.out.println(semaphore.availablePermits());
                    }
                }
            };
            pool.execute(runnable);
        }
        pool.shutdown();
    }

}
