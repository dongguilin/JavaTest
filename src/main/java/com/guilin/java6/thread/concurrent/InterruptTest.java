package com.guilin.java6.thread.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by guilin1 on 16/2/27.
 */
public class InterruptTest implements Runnable {

    public static void main(String[] args) throws Exception {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(3);

        InterruptTest thread = new InterruptTest();

        ScheduledFuture future = pool.scheduleWithFixedDelay(thread, 0, 3, TimeUnit.SECONDS);

        Thread.sleep(10000);

//        future.cancel(true);

        System.out.println("cancel:" + future.isCancelled());
        System.out.println(pool.isShutdown() + "," + pool.isTerminated());

//        Thread.sleep(15000);


//        future = pool.scheduleWithFixedDelay(thread, 0, 3, TimeUnit.SECONDS);

//        pool.awaitTermination(10000,TimeUnit.SECONDS);

//        Thread.sleep(3000);
//        pool.shutdown();
        System.out.println(pool.isShutdown() + "," + pool.isTerminated());

        Thread.sleep(3000);
        System.out.println(pool.isShutdown() + "," + pool.isTerminated());
        System.out.println("重新启动");

//        pool = Executors.newScheduledThreadPool(3);
        pool.scheduleWithFixedDelay(thread, 0, 3, TimeUnit.SECONDS);


    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        boolean interrupted = Thread.interrupted();
        System.out.println(interrupted);
        if (!interrupted) {
            System.out.println(name + "running...");
        } else {
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("interrupt");
        }
    }


}
