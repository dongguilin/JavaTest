package com.guilin.java.designpattern.template;

/**
 * Created by T57 on 2016/10/13 0013.
 * 具体模板类
 */
public class ConcreteClass2 extends AbstractClass {
    @Override
    protected void doSomething() {
        System.out.println("doSomething2");
    }

    @Override
    protected void doAnything() {
        System.out.println("doAnything2");
    }

    @Override
    protected boolean isDoAnything() {
        return false;
    }
}
