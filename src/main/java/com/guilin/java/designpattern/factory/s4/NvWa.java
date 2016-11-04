package com.guilin.java.designpattern.factory.s4;

import com.guilin.java.designpattern.factory.s2.Human;

/**
 * Created by T57 on 2016/10/31 0031.
 * 多工厂模式
 */
public class NvWa {

    public static void main(String[] args) {
        Human whiteHuman = new WhiteHumanFactory().createHuman();
        whiteHuman.getColor();
        whiteHuman.talk();

        Human blackHuman = new BlackHumanFactory().createHuman();
        blackHuman.getColor();
        blackHuman.talk();

        Human yellowHuman = new YellowHumanFactory().createHuman();
        yellowHuman.getColor();
        yellowHuman.talk();

    }
}
