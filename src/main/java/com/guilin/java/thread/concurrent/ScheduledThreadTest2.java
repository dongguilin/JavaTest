package com.guilin.java.thread.concurrent;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by guilin on 2016/9/2.
 */
public class ScheduledThreadTest2 {

    ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);


    public ScheduledThreadTest2(EventBus eventBus) {


        ScheduledFuture future = pool.scheduleWithFixedDelay(new MyThread(eventBus), 0, 2, TimeUnit.SECONDS);
    }

    private static class MyThread implements Runnable {

        private EventBus eventBus;

        public MyThread(EventBus eventBus) {
            this.eventBus = eventBus;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getId() + "," + Thread.currentThread().getName());
                int a = 1 / 0;
                System.out.println("////");
            } catch (Exception e) {
//                eventBus.post(true);

            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        EventBus eventBus = new EventBus("agent");

        for (int i = 0; i < 5; i++) {

            ScheduledThreadTest2 test = new ScheduledThreadTest2(eventBus);

            eventBus.register(test);

            Thread.sleep(3000);

            eventBus.post(true);
        }


//        Thread.sleep(5000);

//        future.cancel(true);

//        pool.awaitTermination(1,TimeUnit.DAYS);


//        pool.shutdown();

    }

    @Subscribe
    public void listen(boolean shutdown) {
        System.out.println("shutdown:" + shutdown);
        pool.shutdown();
    }

}
