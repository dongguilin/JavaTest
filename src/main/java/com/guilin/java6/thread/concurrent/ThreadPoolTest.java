package com.guilin.java6.thread.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: hadoop
 * Date: 15-3-4
 * Time: 下午7:47
 * To change this template use File | Settings | File Templates.
 */
public class ThreadPoolTest {

    public static void main(String args[]) throws InterruptedException {
// only two threads
        ExecutorService exec = Executors.newFixedThreadPool(2);
        for (int index = 0; index < 100; index++) {
            Runnable run = new Runnable() {
                public void run() {
                    long time = (long) (Math.random() * 1000);
                    System.out.println("Sleeping " + time + "ms" + "," + Thread.currentThread());
                    try {
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                    }
                }
            };
            exec.execute(run);
        }
// must shutdown
        exec.shutdown();
    }


}
