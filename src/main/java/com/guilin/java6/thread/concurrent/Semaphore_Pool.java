package com.guilin.java6.thread.concurrent;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by T57 on 2016/7/9 0009.
 * 信号量
 * 信号量对锁的概念进行了扩展，它可以限定对某一个具体资源的最大可访问线程数
 */
public class Semaphore_Pool {

    public static void main(String[] args) throws InterruptedException {
        final Pool pool = Pool.getInstance();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 200; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Object resource = pool.getItem();
                        System.out.println(resource);
                        Thread.sleep(new Random().nextInt(1000));
                        pool.putItem(resource);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.MINUTES);
    }

    //资源池
    public static class Pool {

        private static Pool instance = new Pool();

        private Pool() {
            initResource();
        }

        public static Pool getInstance() {
            return instance;
        }

        private static final int MAX_ALIVABLE = 100;

        //存放对象池中的复用对象
        protected Object[] items = new Object[MAX_ALIVABLE];

        //用于标识池中的项是否正在被使用
        protected boolean[] used = new boolean[MAX_ALIVABLE];

        private void initResource() {
            for (int i = 0; i < items.length; i++) {
                items[i] = (i + 1);
            }
        }

        //最大可以有100个许可
        private final Semaphore available = new Semaphore(MAX_ALIVABLE);

        //获得一个池内的对象
        public Object getItem() throws InterruptedException {
            available.acquire();//申请一个许可
            return getNextAvailableItem();
        }

        //将给定项放回池内
        public void putItem(Object item) {
            if (markAsUnused(item)) {
                available.release();//新增了一个可用项，释放一个许可，请求资源的线程会被激活一个
            }
        }

        protected synchronized boolean markAsUnused(Object item) {
            for (int i = 0; i < MAX_ALIVABLE; ++i) {
                if (item == items[i]) {
                    if (used[i]) {
                        used[i] = false;
                        return true;
                    } else {
                        return false;
                    }
                }
            }
            return false;
        }

        protected synchronized Object getNextAvailableItem() {
            for (int i = 0; i < MAX_ALIVABLE; ++i) {
                if (!used[i]) {//如果当前项未被使用，则获得它
                    used[i] = true;//将当前项标记为已经使用
                    return items[i];
                }
            }
            return null;
        }

    }


}
