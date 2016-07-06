package com.guilin.java6.thread.concurrent;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by guilin1 on 16/2/25.
 */
public class ScheduledExecutorServiceTest {

    private transient int tnum = 0;

    public void run() {
        String num = "0";

        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);

        final AtomicInteger a = new AtomicInteger(0);


        pool.scheduleAtFixedRate(new IncrementNum(a.getAndIncrement()), 0, 3, TimeUnit.SECONDS);
//        pool.scheduleWithFixedDelay(new IncrementNum(num,tnum),0,3,TimeUnit.SECONDS);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("总结任务数[" + a.get() + "]");
            }
        }, new Date(), 4000);
    }

    public static void main(String[] args) {

        System.out.println("主线程：" + Thread.currentThread());

        new ScheduledExecutorServiceTest().run();


    }

    static class IncrementNum implements Runnable {

//        String numStr;

        int tnum;

        public IncrementNum(int tnum) {
//            this.numStr = numStr;
            this.tnum = tnum;
        }

        @Override
        public void run() {
            System.out.println("线程：" + Thread.currentThread() + ",前" + tnum);
//            numStr = Integer.parseInt(numStr) + 1 + "";
            tnum = tnum + 1;
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程：" + Thread.currentThread() + ",后" + tnum);
        }
    }


}
