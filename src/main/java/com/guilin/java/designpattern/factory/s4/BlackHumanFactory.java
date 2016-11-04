package com.guilin.java.designpattern.factory.s4;

import com.guilin.java.designpattern.factory.s2.BlackHuman;
import com.guilin.java.designpattern.factory.s2.Human;

/**
 * Created by T57 on 2016/10/31 0031.
 */
public class BlackHumanFactory extends AbstractHumanFactory {
    @Override
    public Human createHuman() {
        return new BlackHuman();
    }
}
