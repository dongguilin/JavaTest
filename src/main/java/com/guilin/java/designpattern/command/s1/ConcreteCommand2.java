package com.guilin.java.designpattern.command.s1;

/**
 * Created by T57 on 2016/11/12 0012.
 * 具体的Command类
 */
public class ConcreteCommand2 extends Command {

    public ConcreteCommand2() {
        super(new ConcreteReceiver2());
    }

    public ConcreteCommand2(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void execute() {
        super.receiver.doSomething();
    }
}
