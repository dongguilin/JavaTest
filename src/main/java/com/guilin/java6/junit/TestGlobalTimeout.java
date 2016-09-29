package com.guilin.java6.junit;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by guilin on 2016/9/29.
 */
public class TestGlobalTimeout {
    public static String log;
    private final CountDownLatch latch = new CountDownLatch(1);

    @Rule
//    public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds max per method tested
    public Timeout globalTimeout = new Timeout(100); // 10 seconds max per method tested

    @Test
    public void testSleepForTooLong() throws Exception {
        log += "ran1";
        TimeUnit.MICROSECONDS.sleep(90); // sleep for 100 seconds
    }

    @Test
    public void testSleep() throws InterruptedException {
        TimeUnit.MICROSECONDS.sleep(20);
    }

    @Test
    public void testBlockForever() throws Exception {
        log += "ran2";
        latch.await(); // will block
    }

    @Test
    public void testNormal() {
        System.out.println("hello");
    }
}
