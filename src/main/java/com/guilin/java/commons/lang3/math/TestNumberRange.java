package com.guilin.java.commons.lang3.math;

import org.apache.commons.lang.math.DoubleRange;
import org.apache.commons.lang.math.Range;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * Created by Administrator on 2017/4/23.
 */
public class TestNumberRange {
    @Test
    public void test1() {
        System.out.println(StringUtils.center(" demoNumberRange ", 30, "="));
        Range normalScoreRange = new DoubleRange(90, 120);
        double score1 = 102.5;
        double score2 = 79.9;
        System.out.println("Normal score rangeis: " + normalScoreRange);
        System.out.println("Is "
                + score1
                + "a normal score? "
                + StringUtils
                .capitalize(BooleanUtils.toStringYesNo(normalScoreRange
                        .containsDouble(score1))) + ".");
        System.out.println("Is "
                + score2
                + "a normal score? "
                + StringUtils
                .capitalize(BooleanUtils.toStringYesNo(normalScoreRange
                        .containsDouble(score2))) + ".");
        System.out.println();
    }
}
