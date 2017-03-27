package com.guilin.java.proxy.e1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by hadoop on 2015/12/28.
 * 动态代理
 */
public class DynamicProxyTest {

    public static void main(String[] args) {
        final Calculator calculator = new CalculatorImpl();
        LogHandler lh = new LogHandler(calculator);

        Calculator proxy = (Calculator) Proxy.newProxyInstance(Calculator.class.getClassLoader(), new Class<?>[]{Calculator.class}, lh);
        assertNotNull(proxy);
        assertEquals(5, proxy.add(2, 3));


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
        assertNotNull(proxyInstance2);
        assertEquals(5, proxyInstance2.add(2, 3));
    }

}
