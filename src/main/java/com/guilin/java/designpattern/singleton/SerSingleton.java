package com.guilin.java.designpattern.singleton;

import java.io.Serializable;

/**
 * Created by T57 on 2016/8/21 0021.
 */
public class SerSingleton implements Serializable {

    private SerSingleton() {
    }

    private static SerSingleton instance = new SerSingleton();

    public static SerSingleton getInstance() {
        return instance;
    }

    private Object readResolve() {
        return instance;//阻止生成新的实例，总是返回当前对象
    }
}
