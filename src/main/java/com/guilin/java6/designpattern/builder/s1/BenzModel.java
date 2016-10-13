package com.guilin.java6.designpattern.builder.s1;

/**
 * Created by T57 on 2016/10/13 0013.
 * 奔驰模型
 */
public class BenzModel extends CarModel {
    @Override
    protected void start() {
        System.out.println("奔驰start");
    }

    @Override
    protected void stop() {
        System.out.println("奔驰stop");
    }

    @Override
    protected void alarm() {
        System.out.println("奔驰alarm");
    }

    @Override
    protected void engineBoom() {
        System.out.println("奔驰engineBoom");
    }
}
