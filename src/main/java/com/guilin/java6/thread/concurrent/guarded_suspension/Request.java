package com.guilin.java6.thread.concurrent.guarded_suspension;

/**
 * Created by T57 on 2016/7/24 0024.
 * 客户端请求
 */
public class Request {

    private String name;

    public Request(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[ Request " + name + " ]";
    }
}
