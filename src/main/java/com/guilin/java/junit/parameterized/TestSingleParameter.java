package com.guilin.java.junit.parameterized;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.junit.Assert.assertThat;

/**
 * Created by guilin on 2016/9/29.
 */
@RunWith(Parameterized.class)
public class TestSingleParameter {

    @Parameterized.Parameters
    public static Iterable<? extends Object> data() {
        return Arrays.asList("first test", "second test");
    }

    @Parameterized.Parameter
    public String name;

    @Test
    public void test1() {
        assertThat(name, endsWith("test"));
    }
}
