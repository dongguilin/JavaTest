package com.guilin.java6.designpattern.proxy;

/**
 * Created by T57 on 2016/8/21 0021.
 */
public class DBQueryProxy implements IDBQuery {

    private DBQuery real = null;

    @Override
    public String request() {
        if (real == null) {
            real = new DBQuery();
        }
        return real.request();
    }
}
