package com.guilin.java.designpattern.singleton;

/**
 * Created by T57 on 2016/8/21 0021.
 */
public class StaticSingleton {

    private StaticSingleton() {
    }

    //使用内部类来维护单例的实例
    private static class SingletonHolder {
        private static StaticSingleton instance = new StaticSingleton();
    }

    public static StaticSingleton getInstance() {
        return SingletonHolder.instance;
    }
}
