package com.guilin.java6.designpattern.factory.s2;

/**
 * Created by T57 on 2016/10/29 0029.
 */
public class WhiteHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("白色人种的皮肤颜色是白色的");
    }

    @Override
    public void talk() {
        System.out.println("白色和种会说话，一般都是单字节");
    }
}
