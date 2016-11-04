package com.guilin.java.designpattern.factory.s2;

/**
 * Created by T57 on 2016/10/29 0029.
 */
public class YellowHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("黄色人种的皮肤颜色是黄色的");
    }

    @Override
    public void talk() {
        System.out.println("黄色人种会说话，一般说的都是双字节");
    }
}
