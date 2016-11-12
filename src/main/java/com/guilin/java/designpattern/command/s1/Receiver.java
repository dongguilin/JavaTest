package com.guilin.java.designpattern.command.s1;

/**
 * Created by T57 on 2016/11/12 0012.
 * 通用Receiver类
 */
public abstract class Receiver {
    //抽象接收者，定义每个接收者都必须完成的业务
    public abstract void doSomething();
}
