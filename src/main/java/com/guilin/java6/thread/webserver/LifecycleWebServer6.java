package com.guilin.java6.thread.webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by hadoop on 2015/12/20.
 * 支持关闭操作的Web服务器
 */
public class LifecycleWebServer6 {

    private final ServerSocket serverSocket;
    private final ExecutorService pool;

    public LifecycleWebServer6(int port, int poolSize) throws IOException {
        serverSocket = new ServerSocket(port);
        pool = Executors.newFixedThreadPool(poolSize);
    }

    public void start() {
        while (!pool.isShutdown()) {
            try {
                final Socket socket = serverSocket.accept();
                pool.execute(new Runnable() {
                    @Override
                    public void run() {
                        handleRequest(socket);
                    }
                });
            } catch (RejectedExecutionException e) {
                if (!pool.isShutdown()) {
                    System.out.println("task submission rejected!");
                }
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        pool.shutdown();
    }

    public void shutdownAndAwaitTermination(ExecutorService pool) {
        pool.shutdown(); // Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!pool.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }

    private void handleRequest(Socket connection) {
        Request req = readRequest(connection);
        if (isShutdownRequest(req)) {
            stop();
        } else {
            dispatchRequest(req);
        }
    }

    private void dispatchRequest(Request req) {

    }

    private boolean isShutdownRequest(Request req) {
        return Boolean.parseBoolean(req.getParameter("shutdown"));
    }

    private Request readRequest(Socket connection) {
        return new Request(connection);
    }

}
