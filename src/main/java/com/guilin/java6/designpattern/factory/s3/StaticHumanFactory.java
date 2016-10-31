package com.guilin.java6.designpattern.factory.s3;

import com.guilin.java6.designpattern.factory.s2.Human;

/**
 * Created by T57 on 2016/10/31 0031.
 * 简单工厂模式中的工厂类
 */
public class StaticHumanFactory {

    public static <T extends Human> T createHuman(Class<T> c) {
        Human human = null;
        try {
            human = (Human) Class.forName(c.getName()).newInstance();
        } catch (Exception e) {
            System.out.println("人种生成错误");
        }
        return (T) human;
    }
}
