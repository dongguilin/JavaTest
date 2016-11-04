package com.guilin.java.designpattern.builder.s2;

/**
 * Created by T57 on 2016/10/13 0013.
 * 抽象建造者
 */
public abstract class Builder {
    //设置产品的不同部分，以获得不同的产品
    public abstract void setPart();

    //制造产品
    public abstract Product buildProduct();
}
