package com.guilin.java.designpattern.proxy.s4;

/**
 * Created by T57 on 2016/11/7 0007.
 * 真实主题
 */
public class RealSubject implements Subject {
    @Override
    public void doSomething(String str) {
        System.out.println("do something " + str);
    }
}
