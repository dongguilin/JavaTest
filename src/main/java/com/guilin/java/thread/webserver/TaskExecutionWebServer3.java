package com.guilin.java.thread.webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by hadoop on 2015/12/20.
 * 基于固定长度的线程池的Web服务器
 * 尽管服务器不会因为创建了过多的线程而失败，但在足够长的时间内，如果任务到达的速度总是超过任务执行的速度，那么服务器仍
 * 有可能耗尽内存，因为等待执行的Runnable执行队列将不断增长，可以通过使用一个有界工作队列在Executro框架内部解决这个问题
 */
public class TaskExecutionWebServer3 {

    private static final int NTHREADS = 100;
    private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);

    public TaskExecutionWebServer3() throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            final Socket connection = socket.accept();
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    handleRequest(connection);
                }
            };
            exec.execute(task);
        }
    }

    private void handleRequest(Socket connection) {

    }
}
