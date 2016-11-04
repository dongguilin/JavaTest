package com.guilin.java.designpattern.strategy.s1;

/**
 * Created by T57 on 2016/9/11 0011.
 * 封装角色
 */
public class Context {
    //抽象策略
    private Strategy strategy = null;

    //构造函数设置具体策略
    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    //封装后的策略方法
    public void doAnything() {
        strategy.doString();
    }
}
