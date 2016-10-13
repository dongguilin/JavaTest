package com.guilin.java6.junit;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by guilin on 2016/9/29.
 */
public class TestIgnore {

    @Ignore("Test is ignored as a demonstration")
    @Test
    public void testSame() {
        System.out.println("hello");
        assertThat(1, is(1));
    }
}
