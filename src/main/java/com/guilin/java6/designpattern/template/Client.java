package com.guilin.java6.designpattern.template;

/**
 * Created by T57 on 2016/10/13 0013.
 */
public class Client {

    public static void main(String[] args) {
        AbstractClass class1 = new ConcreteClass1();
        AbstractClass class2 = new ConcreteClass2();
        class1.templateMethod();
        class2.templateMethod();
    }
}
