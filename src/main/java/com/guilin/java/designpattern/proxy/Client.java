package com.guilin.java.designpattern.proxy;

/**
 * Created by T57 on 2016/8/21 0021.
 */
public class Client {

    public static void main(String[] args) {
        IDBQuery query = new DBQueryProxy();
        query.request();
    }
}
