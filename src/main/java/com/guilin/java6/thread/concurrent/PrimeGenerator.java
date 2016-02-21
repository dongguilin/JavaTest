package com.guilin.java6.thread.concurrent;

import org.bson.util.annotations.GuardedBy;
import org.bson.util.annotations.ThreadSafe;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hadoop on 2016/2/21.
 */
@ThreadSafe
public class PrimeGenerator implements Runnable {

    @GuardedBy("this")
    private final List<BigInteger> primes = new ArrayList<>();

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

    public static void main(String[] args) throws InterruptedException {
        List<BigInteger> primes = new PrimeGenerator().aSecondOfPrimes();
        System.out.println(primes);
    }
}
