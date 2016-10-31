package com.guilin.java6.designpattern.factory.s1;

/**
 * Created by T57 on 2016/10/29 0029.
 * 抽象工厂类
 */
public abstract class Creator {

    public abstract <T extends Product> T createProduct(Class<T> c);
}
