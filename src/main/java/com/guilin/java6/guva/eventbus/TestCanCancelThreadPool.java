package com.guilin.java6.guva.eventbus;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by guilin on 2016/9/2.
 * 在线程池中启动定时任务，定时任务达到某个状态时，通知线程池关闭该定时任务，没有线程运行时，关闭线程池
 */
public class TestCanCancelThreadPool {

    private ScheduledExecutorService pool;//线程池

    private Map<Integer, ScheduledFuture> futureMap;//保存启动的线程

    private int num;//当前关闭的线程数

    private int total;//总线程数


    public TestCanCancelThreadPool(int total) {
        this.total = total;

        pool = Executors.newScheduledThreadPool(5);

        futureMap = new LinkedHashMap<>();

    }

    public void schedule(EventBus eventBus, int id) {
        ScheduledFuture future = pool.scheduleWithFixedDelay(new MyJob(eventBus, id), 0, 2, TimeUnit.SECONDS);
        futureMap.put(id, future);
    }

    @Subscribe
    public void cancel(Integer id) {
        futureMap.get(id).cancel(true);
        ++num;
        if (num >= total) {
            pool.shutdown();
        }
    }

    public static void main(String[] args) {
        TestCanCancelThreadPool application = new TestCanCancelThreadPool(3);

        EventBus eventBus = new EventBus("myapp");

        eventBus.register(application);

        for (int i = 0; i < 3; i++) {
            application.schedule(eventBus, i);
        }

    }

    public static class MyJob implements Runnable {

        private int id;

        private Random random = new Random();

        private EventBus eventBus;

        public MyJob(EventBus eventBus, int id) {
            this.eventBus = eventBus;
            this.id = id;
        }

        @Override
        public void run() {
            int a = random.nextInt(5);
            System.out.println("random" + a);
            if (a > 3) {
                System.out.println("posting:" + id);
                eventBus.post(id);
                System.out.println("post:" + id + " finish");
            }
        }
    }

}
