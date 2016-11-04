package com.guilin.java.designpattern.factory.s3;

import com.guilin.java.designpattern.factory.s2.BlackHuman;
import com.guilin.java.designpattern.factory.s2.Human;
import com.guilin.java.designpattern.factory.s2.WhiteHuman;
import com.guilin.java.designpattern.factory.s2.YellowHuman;

/**
 * Created by T57 on 2016/10/31 0031.
 * 简单工厂模式
 */
public class NvWa {

    public static void main(String[] args) {
        Human whiteHuman = StaticHumanFactory.createHuman(WhiteHuman.class);
        whiteHuman.getColor();
        whiteHuman.talk();

        Human blackHuman = StaticHumanFactory.createHuman(BlackHuman.class);
        blackHuman.getColor();
        blackHuman.talk();

        Human yellowHuman = StaticHumanFactory.createHuman(YellowHuman.class);
        yellowHuman.getColor();
        yellowHuman.talk();
    }
}
