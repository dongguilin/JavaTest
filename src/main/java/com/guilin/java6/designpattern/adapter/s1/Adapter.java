package com.guilin.java6.designpattern.adapter.s1;

/**
 * Created by T57 on 2016/9/12 0012.
 * 适配器角色
 */
public class Adapter extends Adaptee implements Target {
    @Override
    public void request() {
        super.doString();
    }
}
