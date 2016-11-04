package com.guilin.java.reference;

/**
 * Created by guilin on 2016/9/8.
 */
public class MyObject {

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("MyObject finalize called");//被回收时调用
    }

    @Override
    public String toString() {
        return "I am MyObject";
    }
}
