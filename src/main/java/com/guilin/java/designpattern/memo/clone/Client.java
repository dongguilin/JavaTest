package com.guilin.java.designpattern.memo.clone;

/**
 * Created by T57 on 2016/9/9 0009.
 */
public class Client {

    public static void main(String[] args) {
        //定义发起人
        Originator originator = new Originator();
        //建立初始状态
        originator.setState("hello");
        System.out.println("初始状态:" + originator.getState());

        //建立备份
        originator.createMemo();

        //修改状态
        originator.setState("world");
        System.out.println("修改后的状态:" + originator.getState());

        //恢复原有状态
        originator.restoreMemo();
        System.out.println("恢复后的状态:" + originator.getState());


    }
}
