package com.guilin.java6.designpattern.abstractfactory.s1;

/**
 * Created by T57 on 2016/10/31 0031.
 * 女娲造人类
 */
public class NvWa {
    public static void main(String[] args) {
        HumanFactory maleHumanFactory = new MaleFactory();
        HumanFactory femaleHumanFactory = new FemaleFactory();

        Human maleYellowHuman = maleHumanFactory.createYellowHuman();
        Human femaleYellowHuman = femaleHumanFactory.createYellowHuman();

        maleYellowHuman.getColor();
        maleYellowHuman.talk();
    }
}
