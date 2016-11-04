package com.guilin.java.designpattern.abstractfactory.s2;

/**
 * Created by T57 on 2016/10/31 0031.
 */
public class Client {
    public static void main(String[] args) {
        AbstractCreator creator1 = new Creator1();
        AbstractCreator creator2 = new Creator2();

        AbstractProductA a1 = creator1.createProductA();
        AbstractProductA a2 = creator2.createProductA();

        AbstractProductB b1 = creator1.createProductB();
        AbstractProductB b2 = creator2.createProductB();

    }
}
