package com.guilin.java.designpattern.factory.s1;

/**
 * Created by T57 on 2016/10/29 0029.
 */
public class Client {
    public static void main(String[] args) {
        Creator creator = new ConcreteCreator();
        Product product = creator.createProduct(ConcreteProduct1.class);
    }
}
