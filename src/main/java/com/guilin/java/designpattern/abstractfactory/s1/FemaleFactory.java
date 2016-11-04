package com.guilin.java.designpattern.abstractfactory.s1;

/**
 * Created by T57 on 2016/10/31 0031.
 * 生产女性八卦炉
 */
public class FemaleFactory implements HumanFactory {
    @Override
    public Human createYellowHuman() {
        return new FemaleYellowHuman();
    }

    @Override
    public Human createWhiteHuman() {
        return null;
    }

    @Override
    public Human createBlackHuman() {
        return null;
    }
}
