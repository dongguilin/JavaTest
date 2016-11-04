package com.guilin.java.designpattern.responsibilitychain.s2;

/**
 * Created by T57 on 2016/9/12 0012.
 * 模式中的有关框架代码
 */
public class Request {

    private Level level;

    public Request(Level level) {
        this.level = level;
    }

    //请求的等级
    public Level getRequestLevel() {
        return level;
    }
}
