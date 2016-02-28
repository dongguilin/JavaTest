package com.guilin.java6.thread.concurrent.canceled;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by hadoop on 2016/2/28.
 * 阻塞任务，须使用中断取消操作
 */
public class BlockedTaskCancelTest {

    public static void main(String[] args) {
        //阻塞队列最大容量20
        BlockingQueue<BigInteger> primes = new ArrayBlockingQueue<BigInteger>(20);
        BlockedPrimeGenerator producer = new BlockedPrimeGenerator(primes);
        Thread thread = new Thread(producer);
        thread.start();
        try {
            //休息5秒，BlockingQueue容量达到最大，put方法会一直阻塞
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //中断线程
            System.out.println("中断线程：" + thread.getName());
            thread.interrupt();
        }

    }

    static class BlockedPrimeGenerator implements Runnable {
        private final BlockingQueue<BigInteger> queue;

        public BlockedPrimeGenerator(BlockingQueue<BigInteger> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            BigInteger prime = BigInteger.ONE;
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    prime = prime.nextProbablePrime();
                    System.out.println(Thread.currentThread().getName() + " add " + prime);
                    //达到最大容量时，阻塞，但能够响应中断
                    queue.put(prime);
                }
            } catch (InterruptedException e) {
                System.out.println("线程：" + Thread.currentThread().getName() + "响应中断");
            }
        }
    }
}
