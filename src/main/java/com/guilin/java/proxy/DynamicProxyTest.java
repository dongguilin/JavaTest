package com.guilin.java.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by hadoop on 2015/12/28.
 * 动态代理
 */
public class DynamicProxyTest {

    public static void main(String[] args) {
        final Calculator calculator = new CalculatorImpl();
        LogHandler lh = new LogHandler(calculator);

        //wrong
//        Calculator proxy = (Calculator) Proxy.newProxyInstance(Calculator.class.getClassLoader(), Calculator.class.getInterfaces(), lh);

        //right
        Calculator proxy = (Calculator) Proxy.newProxyInstance(calculator.getClass().getClassLoader(), calculator.getClass().getInterfaces(), lh);
        int result = proxy.add(2, 3);
        System.out.println(result);


        Calculator proxyInstance2 = (Calculator) Proxy.newProxyInstance(calculator.getClass().getClassLoader(), calculator.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("before");
                        Object obj = method.invoke(calculator, args);
                        System.out.println("after");
                        return obj;
                    }
                });

        System.out.println(proxyInstance2.minus(4, 5));
    }

}
