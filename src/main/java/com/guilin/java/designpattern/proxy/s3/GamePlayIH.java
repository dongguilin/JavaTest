package com.guilin.java.designpattern.proxy.s3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by T57 on 2016/11/7 0007.
 */
public class GamePlayIH implements InvocationHandler {

    //被代理的实例
    Object obj = null;

    //我要代理谁
    public GamePlayIH(Object _obj) {
        this.obj = _obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy invoke...");
        Object result = method.invoke(this.obj, args);
        return result;
    }
}
