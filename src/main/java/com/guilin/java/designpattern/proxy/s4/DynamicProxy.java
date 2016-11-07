package com.guilin.java.designpattern.proxy.s4;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by T57 on 2016/11/7 0007.
 * 动态代理类
 */
public class DynamicProxy<T> {
    public static <T> T newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h) {
        if (true) {
            //执行一个前置通知
            new BeforeAdvice().exec();
        }
        //执行目标，并返回结果
        return (T) Proxy.newProxyInstance(loader, interfaces, h);
    }
}
