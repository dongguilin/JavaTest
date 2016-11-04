package com.guilin.java.designpattern.strategy.s2;

/**
 * Created by T57 on 2016/9/11 0011.
 * 策略枚举
 */
public enum Calculator {

    //加法运算
    ADD("+") {
        public int exec(int a, int b) {
            return a + b;
        }
    },

    //减法运算
    SUB("-") {
        public int exec(int a, int b) {
            return a - b;
        }
    };

    //声明一个抽象函数
    public abstract int exec(int a, int b);

    private String symbol = "";

    private Calculator(String symbol) {
        this.symbol = symbol;
    }


}
