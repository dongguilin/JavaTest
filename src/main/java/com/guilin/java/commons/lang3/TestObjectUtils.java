package com.guilin.java.commons.lang3;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Administrator on 2017/4/23.
 */
public class TestObjectUtils {

    @Test
    public void test1() {
        String str = null;
        assertThat(ObjectUtils.defaultIfNull(str, "hello"), is("hello"));
        int a = ObjectUtils.CONST(12);
        a = 13;
    }
}
