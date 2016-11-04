package com.guilin.java.designpattern.abstractfactory.s1;

/**
 * Created by T57 on 2016/10/31 0031.
 */
public abstract class AbstractWhiteHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("white color");
    }

    @Override
    public void talk() {
        System.out.println("say english");
    }
}
