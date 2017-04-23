package com.guilin.java.commons.lang3.time;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

/**
 * Created by Administrator on 2017/4/23.
 */
public class TestStopWatch {
    @Test
    public void test1() {
        System.out.println(StringUtils.center(" demoStopWatch ", 30, "="));
        StopWatch sw = new StopWatch();
        sw.start();
        operationA();
        sw.stop();
        System.out.println("operationA used " + sw.getTime() + " milliseconds.");
        System.out.println();
    }

    public static void operationA() {
        try {
            Thread.sleep(999);
        } catch (InterruptedException e) {
            // do nothing
        }
    }
}
