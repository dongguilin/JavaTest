package com.guilin.java6.designpattern.factory.s2;

/**
 * Created by T57 on 2016/10/29 0029.
 * 抽象人类创建工厂
 */
public abstract class AbstractHumanFactory {
    public abstract <T extends Human> Human createHuman(Class<T> c);
}
