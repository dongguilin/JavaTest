package com.guilin.java.designpattern.mediator.s1;

/**
 * Created by T57 on 2016/11/10 0010.
 * 抽象同事类
 */
public abstract class Colleague {
    protected Mediator mediator;

    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }
}
