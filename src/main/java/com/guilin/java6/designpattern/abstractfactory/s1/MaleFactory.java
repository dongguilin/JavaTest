package com.guilin.java6.designpattern.abstractfactory.s1;

/**
 * Created by T57 on 2016/10/31 0031.
 * 生产男性八卦炉
 */
public class MaleFactory implements HumanFactory {
    @Override
    public Human createYellowHuman() {
        return new MaleYellowHuman();
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
