package com.guilin.java.guava.reflection;

import com.google.common.reflect.AbstractInvocationHandler;
import com.google.common.reflect.Reflection;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by guilin on 2016/11/4.
 */
public class TestDynamicProxy {

    @Test
    public void testJDKProxy() {
        InvocationHandler invocationHandler = new MyInvocationHandler(new IFoo() {
            @Override
            public void doSomething() {
                System.out.println("hello");
            }
        });
        IFoo jdkFoo = (IFoo) Proxy.newProxyInstance(IFoo.class.getClassLoader(), new Class<?>[]{IFoo.class}, invocationHandler);
        jdkFoo.doSomething();
        System.out.println(jdkFoo);
    }

    //guava会过滤掉hashCode、toString、equals方法的代理
    @Test
    public void testGuavaProxy() {
        IFoo real = new IFoo() {
            @Override
            public void doSomething() {
                System.out.println("hello");
            }
        };
        IFoo foo = Reflection.newProxy(IFoo.class, new MyInvocationHandler(real));
        System.out.println("jdk InvocationHandler");
        foo.doSomething();
        System.out.println(foo);
        System.out.println("--------------------");

        IFoo foo2 = Reflection.newProxy(IFoo.class, new GuavaInvocationHandler(real));
        System.out.println("guava AbstractInvocationHandler");
        foo2.doSomething();
        System.out.println(foo2);
        System.out.println("--------------------");

        IFoo foo3 = Reflection.newProxy(IFoo.class, new GuavaInvocationHandler(real));
        System.out.println(foo2.equals(foo3));

    }

    static class GuavaInvocationHandler extends AbstractInvocationHandler {

        private Object object;

        public GuavaInvocationHandler(Object object) {
            this.object = object;
        }

        @Override
        protected Object handleInvocation(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("guava invoke");
            return method.invoke(object, args);
        }
    }

    static class MyInvocationHandler implements InvocationHandler {

        private Object object;

        public MyInvocationHandler(Object object) {
            this.object = object;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("jdk invoke");
            return method.invoke(object, args);
        }
    }

    static interface IFoo {
        void doSomething();
    }
}
