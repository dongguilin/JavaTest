package com.guilin.java6.thread.concurrent.guarded_suspension;

/**
 * Created by T57 on 2016/7/24 0024.
 * 服务器进程
 */
public class ServerThread extends Thread {

    private RequestQueue requestQueue;//请求队列

    public ServerThread(RequestQueue requestQueue, String name) {
        super(name);
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        while (true) {
            final Request request = requestQueue.getRequest();//得到请求
            try {
                Thread.sleep(100);//模拟请求处理耗时
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " handles " + request);
        }
    }
}
