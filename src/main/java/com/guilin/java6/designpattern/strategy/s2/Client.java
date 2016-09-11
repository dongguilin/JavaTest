package com.guilin.java6.designpattern.strategy.s2;

/**
 * Created by T57 on 2016/9/11 0011.
 */
public class Client {

    public static void main(String[] args) {
        int a = 12;
        int b = 13;
        System.out.println(Calculator.ADD.exec(a, b));
        System.out.println(Calculator.SUB.exec(a, b));
    }
}
