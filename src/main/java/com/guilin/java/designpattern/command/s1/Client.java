package com.guilin.java.designpattern.command.s1;

/**
 * Created by T57 on 2016/11/12 0012.
 */
public class Client {
    public static void main(String[] args) {
        Invoker invoker = new Invoker();
        Command command = new ConcreteCommand1();
        invoker.setCommand(command);
        invoker.action();
    }
}
