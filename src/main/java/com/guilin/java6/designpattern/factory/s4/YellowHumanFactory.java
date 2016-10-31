package com.guilin.java6.designpattern.factory.s4;

import com.guilin.java6.designpattern.factory.s2.Human;
import com.guilin.java6.designpattern.factory.s2.YellowHuman;

/**
 * Created by T57 on 2016/10/31 0031.
 */
public class YellowHumanFactory extends AbstractHumanFactory {
    @Override
    public Human createHuman() {
        return new YellowHuman();
    }
}
