package com.guilin.java.thread.concurrent.guarded_suspension;

import com.guilin.java.thread.concurrent.future.Data;

/**
 * Created by T57 on 2016/7/24 0024.
 * 客户端请求
 */
public class Request {

    private String name;

    private Data response;

    public Request(String name) {
        this.name = name;
    }

    public synchronized Data getResponse() {
        return response;
    }

    public synchronized void setResponse(Data response) {
        this.response = response;
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
