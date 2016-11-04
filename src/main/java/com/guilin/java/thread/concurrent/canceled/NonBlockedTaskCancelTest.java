package com.guilin.java.thread.concurrent.canceled;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hadoop on 2016/2/21.
 * 非阻塞任务，可以使用取消标志取消任务
 * 使用协作机制设置某个“已请求取消”标志，任务定期查看该标志，如果设置了这个标志，那么任务将提前结束
 */
public class NonBlockedTaskCancelTest {

    public static void main(String[] args) throws InterruptedException {
        List<BigInteger> primes = new PrimeGenerator().aSecondOfPrimes();
        System.out.println(primes);
    }

    //PrimeGenerator持续地枚举素数，直到它被取消
    static class PrimeGenerator implements Runnable {

        private final List<BigInteger> primes = new ArrayList<>();
        //使用volatile类型的域来保存取消状态
        private volatile boolean cancelled;

        @Override
        public void run() {
            BigInteger p = BigInteger.ONE;
            while (!cancelled) {
                p = p.nextProbablePrime();
                synchronized (this) {
                    primes.add(p);
                }
            }
        }

        public void cancel() {
            cancelled = true;
        }

        public synchronized List<BigInteger> get() {
            return new ArrayList<BigInteger>(primes);
        }

        List<BigInteger> aSecondOfPrimes() throws InterruptedException {
            PrimeGenerator generator = new PrimeGenerator();
            new Thread(generator).start();
            try {
                Thread.sleep(1000);
            } finally {
                generator.cancel();
            }
            return generator.get();
        }
    }
}
