package com.guilin.java6.junit;

import org.junit.Test;

/**
 * Created by guilin on 2016/9/29.
 */
public class TestTimeout {

    @Test(timeout = 1000)
    public void testWithTimeout() {
        try {
            Thread.sleep(1000 * 2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
