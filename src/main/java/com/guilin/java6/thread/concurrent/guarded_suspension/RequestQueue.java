package com.guilin.java6.thread.concurrent.guarded_suspension;

import java.util.LinkedList;

/**
 * Created by T57 on 2016/7/24 0024.
 * 用于保存客户端请求队列
 */
public class RequestQueue {

    private LinkedList queue = new LinkedList();

    public synchronized Request getRequest() {
        while (queue.size() == 0) {
            try {
                wait();//等待直到新的Request加入
            } catch (InterruptedException e) {
            }
        }
        return (Request) queue.remove();
    }

    public synchronized void addRequest(Request request) {
        queue.add(request);
        notifyAll();
    }

}
