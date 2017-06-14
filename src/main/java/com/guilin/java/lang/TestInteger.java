package com.guilin.java.lang;

import org.junit.Test;

/**
 * Created by guilin on 2017/5/2.
 */
public class TestInteger {

    @Test
    public void test1() {
        //[-128,127]之间会从缓冲区中拿，比较时要用equals，避免使用==
        Integer a = 12;
        Integer b = 12;
        Integer c = 1000;
        Integer d = 1000;
        System.out.println(a == b);//true
        System.out.println(c == d);//false
        System.out.println(a.equals(b));//true
        System.out.println(c.equals(d));//true
    }
}
