package com.guilin.java.designpattern.mediator.s1;

/**
 * Created by T57 on 2016/11/10 0010.
 * 通用抽象中介者
 */
public abstract class Mediator {

    //定义同事类
    protected ConcreteColleague1 c1;
    protected ConcreteColleague2 c2;

    public ConcreteColleague1 getC1() {
        return c1;
    }

    public void setC1(ConcreteColleague1 c1) {
        this.c1 = c1;
    }

    public ConcreteColleague2 getC2() {
        return c2;
    }

    public void setC2(ConcreteColleague2 c2) {
        this.c2 = c2;
    }

    //业务逻辑
    public abstract void doSomething1();

    public abstract void doSomething2();
}
