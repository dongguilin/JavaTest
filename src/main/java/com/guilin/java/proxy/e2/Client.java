package com.guilin.java.proxy.e2;

/**
 * Created by guilin on 2017/6/14.
 */
public class Client {

    public static void main(String[] args) {
        AbstractUserService userService1 = new UserService1();
        userService1.invoke("张三", "aa");

        System.out.println("/////////////");

        AbstractUserService userService2 = new UserService2();
        userService2.invoke("李四", "aa");
    }
}
