package com.guilin.java.designpattern.responsibilitychain.s2;

/**
 * Created by T57 on 2016/9/12 0012.
 * 具体处理者
 */
public class ConcreteHandler1 extends Handler {
    @Override
    Level getHandlerLevel() {
        return Level.a;
    }

    @Override
    Response echo(Request request) {
        return new Response("level:" + request.getRequestLevel());
    }
}
