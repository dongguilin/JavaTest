package com.guilin.java6.designpattern.adapter.s1;

/**
 * Created by T57 on 2016/9/12 0012.
 * 目标角色的实现类
 */
public class ConcreteTarget implements Target {
    @Override
    public void request() {
        System.out.println("if you need any help,pls call me");
    }
}
