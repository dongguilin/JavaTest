package com.guilin.java6.designpattern.factory.s5;

import java.lang.reflect.Constructor;

/**
 * Created by T57 on 2016/10/31 0031.
 * 使用工厂模式实现单例
 */
public class SingletonFactory {

    private static Singleton singleton;

    static {
        try {
            Class cl = Class.forName(Singleton.class.getName());
            Constructor constructor = cl.getDeclaredConstructor();
            constructor.setAccessible(true);
            singleton = (Singleton) constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Singleton getSingleton() {
        return singleton;
    }
}
