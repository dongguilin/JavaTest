package com.guilin.java.designpattern.mediator.s1;

/**
 * Created by T57 on 2016/11/10 0010.
 * 具体同事类
 */
public class ConcreteColleague2 extends Colleague {

    //通过构造函数传递中介者
    public ConcreteColleague2(Mediator mediator) {
        super(mediator);
    }

    public void selfMethod2() {

    }

    public void depMethod2() {
        super.mediator.doSomething2();
    }
}
