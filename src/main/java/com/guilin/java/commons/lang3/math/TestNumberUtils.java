package com.guilin.java.commons.lang3.math;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

/**
 * Created by Administrator on 2017/4/23.
 */
public class TestNumberUtils {
    @Test
    public void test1() {
        System.out.println(StringUtils.center(" demoNumberUtils ", 30, "="));
        System.out.println("Is 0x3Fa number? "
                + StringUtils.capitalize(BooleanUtils.toStringYesNo(NumberUtils.isNumber("0x3F"))) + ".");
        double[] array = {1.0, 3.4, 0.8, 7.1, 4.6};
        double max = NumberUtils.max(array);
        double min = NumberUtils.min(array);
        String arrayStr = ArrayUtils.toString(array);
        System.out.println("Max of " + arrayStr + " is: " + max);
        System.out.println("Min of " + arrayStr + " is: " + min);
        System.out.println();
    }
}
