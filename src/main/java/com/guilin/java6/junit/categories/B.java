package com.guilin.java6.junit.categories;

import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * Created by guilin on 2016/9/29.
 */
@Category({SlowTests.class, FastTests.class})
public class B {
    @Test
    public void c() {

    }
}
