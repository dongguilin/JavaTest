package com.guilin.java.junit.rules;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

/**
 * Created by guilin on 2016/9/30.
 * The ErrorCollector Rule allows execution of a test to continue after the first problem is found (for example,
 * to collect all the incorrect rows in a table, and report them all at once)
 */
public class TestErrorCollector {
    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Test
    public void example() {
        collector.addError(new Throwable("first thing went wrong"));
        collector.addError(new Throwable("second thing went wrong"));
    }
}
