package com.guilin.java.designpattern.template;

/**
 * Created by T57 on 2016/10/13 0013.
 * 抽象模板类
 */
public abstract class AbstractClass {
    //基本方法
    protected abstract void doSomething();

    //基本方法
    protected abstract void doAnything();

    //钩子方法
    protected boolean isDoAnything() {
        return true;
    }

    //模板方法(实现对基本方法的调度，完成固定的逻辑)
    public void templateMethod() {
        if (isDoAnything()) {
            this.doAnything();
        }
        this.doSomething();
    }
}
