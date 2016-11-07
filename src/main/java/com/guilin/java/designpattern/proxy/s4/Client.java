package com.guilin.java.designpattern.proxy.s4;

import java.lang.reflect.InvocationHandler;

/**
 * Created by T57 on 2016/11/7 0007.
 */
public class Client {
    public static void main(String[] args) {
        //定义一个主题
        Subject subject = new RealSubject();
        //定义一个handler
        InvocationHandler handler = new MyInvocationHandler(subject);
        //定义主题的代理
        Subject proxy = DynamicProxy.newProxyInstance(subject.getClass().getClassLoader(), subject.getClass().getInterfaces(), handler);
        //代理的行为
        proxy.doSomething("Finish");

    }
}
