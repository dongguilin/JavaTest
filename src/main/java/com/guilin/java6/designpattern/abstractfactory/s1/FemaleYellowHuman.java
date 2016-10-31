package com.guilin.java6.designpattern.abstractfactory.s1;

/**
 * Created by T57 on 2016/10/31 0031.
 * 黄色女性人种
 */
public class FemaleYellowHuman extends AbstractYellowHuman {
    @Override
    public void getSex() {
        System.out.println("黄人女性");
    }
}
