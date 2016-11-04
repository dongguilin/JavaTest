package com.guilin.java.thread.concurrent.guarded_suspension;

import com.guilin.java.thread.concurrent.future.FutureData;
import com.guilin.java.thread.concurrent.future.RealData;

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
            final FutureData futureData = (FutureData) request.getResponse();
            //RealData的创建比较耗时
            RealData realData = new RealData(request.getName());
            //处理完成后，通知客户进程
            futureData.setRealData(realData);

            System.out.println(Thread.currentThread().getName() + " handles " + request);
        }
    }
}
