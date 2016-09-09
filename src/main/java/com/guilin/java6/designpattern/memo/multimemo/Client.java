package com.guilin.java6.designpattern.memo.multimemo;

/**
 * Created by T57 on 2016/9/9 0009.
 * 多状态、多备份模式备忘录
 */
public class Client {

    public static void main(String[] args) {

        Originator originator = new Originator();
        originator.setState1("hello");
        originator.setState2("world");
        originator.setState3("!");
        System.out.println("初始化状态:" + originator);

        Caretaker caretaker = new Caretaker();
        caretaker.setMemo("001", originator.createMemo());

        originator.setState1("您好");
        originator.setState2("世界");
        originator.setState3("!");
        System.out.println("改变后的状态:" + originator);

        originator.restoreMemo(caretaker.getMemo("001"));
        System.out.println("恢复后的状态:" + originator);

    }
}
