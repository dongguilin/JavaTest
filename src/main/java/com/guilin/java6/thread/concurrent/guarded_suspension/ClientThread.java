package com.guilin.java6.thread.concurrent.guarded_suspension;

import com.guilin.java6.thread.concurrent.future.FutureData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by T57 on 2016/7/24 0024.
 * 客户端进程
 */
public class ClientThread extends Thread {

    private RequestQueue requestQueue;//请求队列

    private List<Request> myRequest = new ArrayList<>();

    public ClientThread(RequestQueue requestQueue, String name) {
        super(name);
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            Request request = new Request("RequestID:" + i + " ThreadName:" + Thread.currentThread().getName());//构造请求
            System.out.println(Thread.currentThread().getName() + " requests " + request);

            //设置一个FutureData的返回值
            request.setResponse(new FutureData());
            requestQueue.addRequest(request);//提交请求
            //发送请求
            myRequest.add(request);

            //模拟其他逻辑，等待服务端装配数据
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }

        //取得服务端的返回值
        for (Request r : myRequest) {
            System.out.println("ClientThread Name is:" + Thread.currentThread().getName() + " Response is:" + r.getResponse().getResult());
        }

    }
}
