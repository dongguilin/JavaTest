package com.guilin.java6.designpattern.factory.s2;

/**
 * Created by T57 on 2016/10/29 0029.
 * 人类创建工厂
 */
public class HumanFactory extends AbstractHumanFactory {
    @Override
    public <T extends Human> T createHuman(Class<T> c) {
        Human human = null;
        try {
            human = (Human) Class.forName(c.getName()).newInstance();
        } catch (Exception e) {
            System.out.println("人种生成错误");
        }
        return (T) human;
    }
}
