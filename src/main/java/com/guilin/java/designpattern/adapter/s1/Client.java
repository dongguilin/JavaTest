package com.guilin.java.designpattern.adapter.s1;

/**
 * Created by T57 on 2016/9/12 0012.
 * 类适配器是类间继承
 */
public class Client {

    public static void main(String[] args) {
        //原有的业务逻辑
        Target target = new ConcreteTarget();
        target.request();
        //增加了适配器角色后的业务逻辑
        target = new Adapter();
        target.request();
    }
}
