package com.guilin.java.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by guilin on 2016/9/29.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestAssertThat.class,
        TestException.class,
        TestGlobalTimeout.class,
        TestIgnore.class,
        TestTimeout.class

})

public class TestSuite {
}
