package com.guilin.java6.designpattern.proxy;

/**
 * Created by T57 on 2016/8/21 0021.
 */
public class DBQuery implements IDBQuery {

    public DBQuery() {
        try {
            Thread.sleep(1000);//可能包含数据库连接等耗时操作
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String request() {
        return "request string";
    }
}
