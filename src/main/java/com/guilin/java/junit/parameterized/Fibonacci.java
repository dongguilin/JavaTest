package com.guilin.java.junit.parameterized;

/**
 * Created by guilin on 2016/9/29.
 */
public class Fibonacci {
    public static int compute(int n) {
        int result = 0;

        if (n <= 1) {
            result = n;
        } else {
            result = compute(n - 1) + compute(n - 2);
        }

        return result;
    }
}
