package com.guilin.java6.designpattern.abstractfactory.s2;

/**
 * Created by T57 on 2016/10/31 0031.
 */
public class Creator1 extends AbstractCreator {
    @Override
    public AbstractProductA createProductA() {
        return new ProductA1();
    }

    @Override
    public AbstractProductB createProductB() {
        return new ProductB1();
    }
}
