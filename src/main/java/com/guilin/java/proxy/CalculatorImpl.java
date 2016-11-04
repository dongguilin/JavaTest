package com.guilin.java.proxy;

/**
 * Created by hadoop on 2015/12/28.
 */
public class CalculatorImpl implements Calculator {
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int minus(int a, int b) {
        return a - b;
    }
}
