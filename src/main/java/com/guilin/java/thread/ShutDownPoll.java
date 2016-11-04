package com.guilin.java.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by guilin on 2016/9/20.
 */
public class ShutDownPoll {

    public static void main(String[] args) {
        final ScheduledThreadPoolExecutor monitorService = new ScheduledThreadPoolExecutor(10,
                new ThreadFactoryBuilder().setNameFormat("lifecycleSupervisor-" + Thread.currentThread().getId() + "-%d").build());
        monitorService.setMaximumPoolSize(10);
        monitorService.setKeepAliveTime(30, TimeUnit.SECONDS);

        monitorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("purge");
                try {
                    Thread.sleep(100000);//模拟长时间任务
                } catch (InterruptedException e) {//可以响应monitorService.shutdownNow()的中断请求
                    System.out.println("interrupt shutdown now");
                    Thread.interrupted();
                }
                monitorService.purge();

                //不能响应中断
//                while(true){}
            }
        }, 0, 2, TimeUnit.HOURS);

        monitorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        }, 1, 1, TimeUnit.HOURS);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //按过去执行已提交任务的顺序发起一个有序的关闭，但是不接受新任务。如果已经关闭，则调用没有其他作用
        monitorService.shutdown();

        try {
            monitorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("Interrupted while waiting for monitor service to stop");
        }
        if (!monitorService.isTerminated()) {
            //尝试停止所有的活动执行任务、暂停等待任务的处理，并返回等待执行的任务列表。在从此方法返回的任务队列中排空（移除）这些任务。
            //此实现通过 Thread.interrupt() 取消任务，所以无法响应中断的任何任务可能永远无法终止
            List<Runnable> list = monitorService.shutdownNow();
            System.out.println(list.size());
            try {
                while (!monitorService.isTerminated()) {
                    boolean flag = monitorService.awaitTermination(2, TimeUnit.SECONDS);
                    System.out.println("timeout:" + !flag);
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupted while waiting for monitor service to stop");
            }
        }

    }
}
