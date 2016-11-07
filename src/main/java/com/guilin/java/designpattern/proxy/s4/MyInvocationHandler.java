package com.guilin.java.designpattern.proxy.s4;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by T57 on 2016/11/7 0007.
 * 动态代理的Handler类
 */
public class MyInvocationHandler implements InvocationHandler {

    //被代理的对象
    private Object target = null;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //执行被代理的方法
        return method.invoke(target, args);
    }
}
