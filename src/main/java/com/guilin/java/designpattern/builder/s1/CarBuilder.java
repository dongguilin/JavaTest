package com.guilin.java.designpattern.builder.s1;

import java.util.List;

/**
 * Created by T57 on 2016/10/13 0013.
 * 抽象汽车组装者
 */
public abstract class CarBuilder {
    public abstract void setSequence(List<String> list);

    public abstract CarModel getCarModel();
}
