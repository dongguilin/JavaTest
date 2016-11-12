package com.guilin.java.designpattern.command.s1;

/**
 * Created by T57 on 2016/11/12 0012.
 * 抽象的Command类
 */
public abstract class Command {

    protected final Receiver receiver;

    public Command(Receiver receiver) {
        this.receiver = receiver;
    }

    //每个命令类都必须有一个执行命令的方法
    public abstract void execute();
}
