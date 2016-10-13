package com.guilin.java6.designpattern.builder.s1;

/**
 * Created by T57 on 2016/10/13 0013.
 */
public class Client {

    public static void main(String[] args) {
        Director director = new Director();
        for (int i = 0; i < 10; i++) {
            director.getABenzModel().run();
        }

        for (int i = 0; i < 2; i++) {
            director.getBBenzModel().run();
        }

        for (int i = 0; i < 3; i++) {
            director.getABMWModel().run();
        }
    }
}
