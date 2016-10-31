package com.guilin.java6.designpattern.factory.s2;

/**
 * Created by T57 on 2016/10/31 0031.
 * 女娲类
 */
public class NvWa {

    public static void main(String[] args) {
        AbstractHumanFactory yiyanglu = new HumanFactory();
        Human whiteHuman = yiyanglu.createHuman(WhiteHuman.class);
        whiteHuman.getColor();
        whiteHuman.talk();

        Human blackHuman = yiyanglu.createHuman(BlackHuman.class);
        blackHuman.getColor();
        blackHuman.talk();

        Human yellowHuman = yiyanglu.createHuman(YellowHuman.class);
        yellowHuman.getColor();
        yellowHuman.talk();
    }
}
