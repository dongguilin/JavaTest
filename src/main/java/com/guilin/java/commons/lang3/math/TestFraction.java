package com.guilin.java.commons.lang3.math;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.Fraction;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Administrator on 2017/4/23.
 * 分数
 */
public class TestFraction {
    @Test
    public void test1() {
        System.out.println(StringUtils.center(" demoFraction ", 30, "="));
        Fraction myFraction = Fraction.getFraction(144, 90);
        assertThat(myFraction.toString(), is("144/90"));
        assertThat(myFraction.toProperString(), is("1 54/90"));
        assertThat(myFraction.doubleValue(), is(1.6));
        assertThat(myFraction.reduce().toString(), is("8/5"));
        assertThat(myFraction.reduce().toProperString(), is("1 3/5"));
        assertThat(Fraction.getFraction("1 54/90").reduce().toProperString(), is("1 3/5"));
    }
}
