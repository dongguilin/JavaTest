package com.guilin.java.designpattern.command.s2;

/**
 * Created by T57 on 2016/11/12 0012.
 */
public class Client {

    public static void main(String[] args) {
        Invoker xiaosan = new Invoker();
        Command command = new AddRequirementCommand();
        xiaosan.setCommand(command);
        xiaosan.action();
    }
}
