package com.guilin.java.thread.concurrent;

import sun.awt.util.IdentityLinkedList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by hadoop on 2015/12/19.
 * 线程池与不使用线程池对比
 * 使用线程池时，线程可复用，无重复创建线程的开销，效率更高
 */
public class ThrealPoolVSNotPoolTest {

    private static int count = 10000;

    public static void main(String[] args) {
        userThreadPool();
        notUseThreadPool();
    }

    public static void userThreadPool() {
        long startTime = System.currentTimeMillis();
        final List<Integer> l = new ArrayList<>();
        ThreadPoolExecutor tp = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(count));
        final Random random = new Random();
        for (int i = 0; i < count; i++) {
            tp.execute(new Runnable() {
                @Override
                public void run() {
                    l.add(random.nextInt());
                }
            });
        }
        tp.shutdown();
        try {
            tp.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("使用线程池耗时：" + (System.currentTimeMillis() - startTime));
        System.out.println(l.size());
    }

    public static void notUseThreadPool() {
        long startTime = System.currentTimeMillis();
        final List<Integer> l = new IdentityLinkedList<>();
        final Random random = new Random();
        for (int i = 0; i < count; i++) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    l.add(random.nextInt());
                }
            };
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("不使用线程池耗时：" + (System.currentTimeMillis() - startTime));
        System.out.println(l.size());

    }
}
