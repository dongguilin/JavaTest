package com.guilin.java6.thread.concurrent;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by hadoop on 2015/12/23.
 * 将数组分10块，使用10个线程分别对10块并行排序
 */
public class CountDownLatch_SortArray {

    public static void main(String[] args) throws InterruptedException {

        Random random = new Random();
        final int[] datas = new int[102400000];
        for (int i = 0; i < datas.length; i++) {
            datas[i] = random.nextInt(102400000);
        }

        sort1(datas);
    }

    public static void sort1(final int[] datas) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        ExecutorService pool = Executors.newFixedThreadPool(10);
        int count = 10;
        final CountDownLatch latch = new CountDownLatch(count);
        int step = datas.length / count;
        for (int i = 0; i < count; i++) {
            final int start = i * step;
            int end = (i + 1) * step;
            if (i == count - 1) end = datas.length;
            final int finalEnd = end;
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    Arrays.sort(datas, start, finalEnd);
                    latch.countDown();
                    System.out.println(Thread.currentThread());
                }
            });
        }
        latch.await();
        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.DAYS);
        System.out.println(System.currentTimeMillis() - startTime);
    }

    //单线程跑
    public static void sort2(final int[] datas) {
        long startTime = System.currentTimeMillis();
        int count = 10;
        int step = datas.length / count;
        for (int i = 0; i < count; i++) {
            int start = i * step;
            int end = (i + 1) * step;
            if (i == count - 1) end = datas.length;
            Arrays.sort(datas, start, end);
        }
        System.out.println(System.currentTimeMillis() - startTime);
    }

}
