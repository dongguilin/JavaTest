package com.guilin.java6.thread.concurrent.guarded_suspension;

/**
 * Created by T57 on 2016/7/24 0024.
 * 客户端进程
 */
public class ClientThread extends Thread {

    private RequestQueue requestQueue;//请求队列

    public ClientThread(RequestQueue requestQueue, String name) {
        super(name);
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            Request request = new Request("RequestID:" + i + " ThreadName:" + Thread.currentThread().getName());//构造请求
            System.out.println(Thread.currentThread().getName() + " requests " + request);
            requestQueue.addRequest(request);//提交请求
            try {
                Thread.sleep(10);//客户端请求的速度，快于服务器处理的速度
            } catch (InterruptedException e) {
            }
            System.out.println("ClientThread Name is:" + Thread.currentThread().getName());
        }
        System.out.println(Thread.currentThread().getName() + " request end");
    }
}
