package com.guilin.java6.designpattern.strategy.s1;

/**
 * Created by T57 on 2016/9/11 0011.
 */
public class Client {

    public static void main(String[] args) {
        //声明一个具体的策略
        Strategy strategy = new ConcreteStrategy1();
        //声明上下文对象
        Context context = new Context(strategy);
        //执行封装后的方法
        context.doAnything();

    }
}
