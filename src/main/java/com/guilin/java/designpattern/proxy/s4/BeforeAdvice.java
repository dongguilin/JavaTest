package com.guilin.java.designpattern.proxy.s4;

/**
 * Created by T57 on 2016/11/7 0007.
 */
public class BeforeAdvice implements IAdvice {
    @Override
    public void exec() {
        System.out.println("我是前置通知，我被执行了");
    }
}
