package com.guilin.java.designpattern.mediator.s1;

/**
 * Created by T57 on 2016/11/10 0010.
 * 通用中介者
 */
public class ConcreteMediator extends Mediator {
    @Override
    public void doSomething1() {
        super.c1.selfMethod1();
        super.c2.selfMethod2();
    }

    @Override
    public void doSomething2() {
        super.c1.selfMethod1();
        super.c2.selfMethod2();
    }
}
