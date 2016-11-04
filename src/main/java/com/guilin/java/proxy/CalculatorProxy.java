package com.guilin.java.proxy;

/**
 * Created by hadoop on 2015/12/28.
 * 静态代理
 */
public class CalculatorProxy implements Calculator {

    private Calculator calculator;

    public CalculatorProxy(Calculator calculator) {
        this.calculator = calculator;
    }

    public static void main(String[] args) {
        CalculatorProxy proxy = new CalculatorProxy(new CalculatorImpl());
        System.out.println(proxy.add(2, 3));
    }

    @Override
    public int add(int a, int b) {
        //before
        System.out.println("calcuting...");
        int result = calculator.add(a, b);
        //after
        System.out.println("finish!");
        return result;
    }

    @Override
    public int minus(int a, int b) {
        return a - b;
    }

}
