package com.guilin.java.proxy.e2;

/**
 * Created by guilin on 2017/6/14.
 */
public class UserService1 extends AbstractUserService {
    @Override
    public void say(String str) {
        System.out.println("hello:" + str);
    }
}
