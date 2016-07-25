package com.guilin.java6.thread.concurrent.producer_consumer;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Created by T57 on 2016/7/25 0025.
 * 消费者
 */
public class Consumer implements Runnable {

    private volatile boolean isRunning = true;

    private BlockingQueue<PCData> queue;

    private static final int SLEEPTIME = 1000;

    public Consumer(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("start consumer id=" + Thread.currentThread().getId());
        Random r = new Random();
        try {
            while (isRunning) {
                PCData data = queue.take();
                if (null != data) {
                    int re = data.getIntData() * data.getIntData();
                    System.out.println(MessageFormat.format("{0}*{1}={2}", data.getIntData(), data.getIntData(), re));
                    Thread.sleep(r.nextInt(SLEEPTIME));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void stop() {
        isRunning = false;
    }
}
