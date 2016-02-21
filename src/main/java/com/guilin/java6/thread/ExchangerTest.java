package com.guilin.java6.thread;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hadoop on 2015/12/26.
 * Exchanger用于在两个线程之间进行数据交换
 */
public class ExchangerTest {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        final Exchanger<String> exchanger = new Exchanger();
        pool.execute(new Runnable() {
            @Override
            public void run() {
                String data1 = "零食";
                System.out.println("线程" + Thread.currentThread().getName() + "正在把" + data1 + "换出去");
                try {
                    Thread.sleep((long) (Math.random() * 1000));
                    String data2 = exchanger.exchange(data1);
                    System.out.println("线程" + Thread.currentThread().getName() + "换回的数据是" + data2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        pool.execute(new Runnable() {
            @Override
            public void run() {
                String data1 = "钱";
                System.out.println("线程" + Thread.currentThread().getName() + "正在把" + data1 + "换出去");
                try {
                    Thread.sleep((long) (Math.random() * 1000));
                    String data2 = exchanger.exchange(data1);
                    System.out.println("线程" + Thread.currentThread().getName() + "换回的数据是" + data2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        pool.shutdown();

    }
}
