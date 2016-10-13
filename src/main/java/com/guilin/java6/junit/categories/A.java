package com.guilin.java6.junit.categories;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import static junit.framework.TestCase.fail;

/**
 * Created by guilin on 2016/9/29.
 */
public class A {
    @Test
    public void a() {
        fail();
    }

    @Category(SlowTests.class)
    @Test
    public void b() {
    }
}
