package com.guilin.java.commons.lang3.math;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * Created by Administrator on 2017/4/23.
 */
public class TestRandomUtils {

    @Test
    public void test1() {
        System.out.println(StringUtils.center(" demoRandomUtils ", 30, "="));
        for (int i = 0; i < 5; i++) {
            System.out.println(RandomUtils.nextInt(100));
        }
        System.out.println();
    }
}
