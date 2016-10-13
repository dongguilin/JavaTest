package com.guilin.java6.designpattern.template;

/**
 * Created by T57 on 2016/10/13 0013.
 * 具体模板类
 */
public class ConcreteClass1 extends AbstractClass {
    @Override
    protected void doSomething() {
        System.out.println("doSomething1");
    }

    @Override
    protected void doAnything() {
        System.out.println("doAnything1");
    }
}
