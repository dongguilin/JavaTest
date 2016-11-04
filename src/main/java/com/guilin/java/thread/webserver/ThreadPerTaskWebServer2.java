package com.guilin.java.thread.webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by hadoop on 2015/12/20.
 * 在Web服务器中为每个请求启动一个新的线程（不要这么做）
 * 缺点：1.线程生命周期的开销非常高
 * 2.资源消耗（如果可运行的线程数量多于可用处理器的数量，那么有些线程将闲置。大量空闲的线程会占用许多内存，给垃圾回收器
 * 带来压力，而且大量线程在竞争cpu资源时还将产生其他的性能开销）
 * 3.稳定性（在可创建线程的数量上存在一个限制，受多个因素制约，包括JVM的启动参数，Thread的构造函数中请求的栈大小，以及底层
 * 操作系统对线程的限制）
 */
public class ThreadPerTaskWebServer2 {

    public ThreadPerTaskWebServer2() throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            final Socket connection = socket.accept();
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    handleRequest(connection);
                }
            };
            new Thread(task).start();
        }
    }

    private void handleRequest(Socket connection) {

    }
}
