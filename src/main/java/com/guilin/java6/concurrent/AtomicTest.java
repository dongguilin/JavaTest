package com.guilin.java6.concurrent;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by guilin1 on 15/9/23.
 */
public class AtomicTest {

    @Test
    public void test1() {
        AtomicBoolean ab = new AtomicBoolean(true);
        System.out.println(ab.compareAndSet(true, false));
        System.out.println(ab.get());

        System.out.println(ab.compareAndSet(true, true));
        System.out.println(ab.get());
    }
}
