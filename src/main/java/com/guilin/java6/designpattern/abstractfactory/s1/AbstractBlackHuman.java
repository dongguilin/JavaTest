package com.guilin.java6.designpattern.abstractfactory.s1;

/**
 * Created by T57 on 2016/10/31 0031.
 */
public abstract class AbstractBlackHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("black color");
    }

    @Override
    public void talk() {
        System.out.println("黑人说话，听不懂");
    }
}
