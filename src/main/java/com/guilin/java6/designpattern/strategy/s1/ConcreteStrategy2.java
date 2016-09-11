package com.guilin.java6.designpattern.strategy.s1;

/**
 * Created by T57 on 2016/9/11 0011.
 * 具体策略角色
 */
public class ConcreteStrategy2 implements Strategy {
    @Override
    public void doString() {
        System.out.println("具体策略2的运算法则");
    }
}
