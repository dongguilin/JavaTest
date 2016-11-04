package com.guilin.java.thread.concurrent;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: hadoop
 * Date: 15-3-4
 * Time: 下午8:01
 * To change this template use File | Settings | File Templates.
 */
public class ScheduledThreadTest {

    public static void main(String[] args) {
        final ScheduledExecutorService scheduler = Executors
                .newScheduledThreadPool(2);
        final Runnable beeper = new Runnable() {
            int count = 0;

            public void run() {
                System.out.println(new Date() + " beep " + (++count));
            }
        };
// 1秒钟后运行，并每隔2秒运行一次
        final ScheduledFuture beeperHandle = scheduler.scheduleAtFixedRate(
                beeper, 1, 2, TimeUnit.SECONDS);
// 2秒钟后运行，并每次在上次任务运行完后等待5秒后重新运行
        final ScheduledFuture beeperHandle2 = scheduler
                .scheduleWithFixedDelay(beeper, 2, 5, TimeUnit.SECONDS);
// 30秒后结束关闭任务，并且关闭Scheduler
        scheduler.schedule(new Runnable() {
            public void run() {
                beeperHandle.cancel(true);
                beeperHandle2.cancel(true);
                scheduler.shutdown();
            }
        }, 30, TimeUnit.SECONDS);
    }

}
