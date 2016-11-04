package com.guilin.java.designpattern.builder.s1;

/**
 * Created by T57 on 2016/10/13 0013.
 * 宝马模型
 */
public class BMWModel extends CarModel {
    @Override
    protected void start() {
        System.out.println("宝马start");
    }

    @Override
    protected void stop() {
        System.out.println("宝马stop");
    }

    @Override
    protected void alarm() {
        System.out.println("宝马alarm");
    }

    @Override
    protected void engineBoom() {
        System.out.println("宝马engineBoom");
    }
}
