package com.guilin.java.proxy.e2;

/**
 * Created by guilin on 2017/6/14.
 */
public abstract class AbstractUserService {

    public abstract void say(String str);

    public void invoke(String a, String b) {
        long start = System.currentTimeMillis();
        say(a);
        long end = System.currentTimeMillis() + 100;
        System.out.println(end - start);
    }

}
