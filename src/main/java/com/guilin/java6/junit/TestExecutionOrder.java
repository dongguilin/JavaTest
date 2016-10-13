package com.guilin.java6.junit;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Created by guilin on 2016/9/29.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestExecutionOrder {
    @Test
    public void testA() {
        System.out.println("first");
    }

    @Test
    public void testB() {
        System.out.println("second");
    }

    @Test
    public void testC() {
        System.out.println("third");
    }
}
