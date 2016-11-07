package com.guilin.java.designpattern.proxy.s2;

/**
 * Created by T57 on 2016/11/7 0007.
 * 代理类
 */
public class Proxy implements Subject {

    //要代理哪个实现类
    private Subject subject = null;

    //默认被代理者
    public Proxy() {
        this.subject = new Proxy();
    }

    //通过构造函数传递代理者,一个代理类可以代理多个被委托者或被代理者
    public Proxy(Object... objects) {

    }

    @Override
    public void request() {
        this.before();
        this.subject.request();
        this.after();
    }

    private void before() {
        //do something
    }

    private void after() {
        //do something
    }
}
