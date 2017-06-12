package com.guilin.java.thread.concurrent.canceled.newtaskfor;


import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

/**
 * Created by guilin on 2017/6/11.
 * SocketUsingTask实现了CancellableTask，并定义了Future.cancel来关闭套接字和调用super.cancel.如果SocketUsingTask通过其
 * 自己的Future来取消，那么底层的套接字将被关闭并且线程将被中断。因此它提高了任务对取消操作的响应性：不仅能够在调用可中断方法的
 * 同时确保响应取消操作，而且还能调用可阻调的套接字I/O方法。
 */
public class SocketUsingTask<T> implements CancellableTask<T> {

    //    @GuardedBy("this")
    private Socket socket;

    protected synchronized void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public synchronized void cancel() {
        try {
            if (socket != null) {
                System.out.println(Thread.currentThread().getName() + " isConnected:" + socket.isConnected() +
                        " isClosed:" + socket.isClosed() + " isBound:" + socket.isBound());
                System.out.println(Thread.currentThread().getName() + " isInputShutdown:" + socket.isInputShutdown() +
                        " isOutputShutdown:" + socket.isOutputShutdown());
                socket.close();
            }
        } catch (IOException ignored) {
        }
    }

    @Override
    public RunnableFuture<T> newTask() {
        return new FutureTask<T>(this) {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                try {
                    SocketUsingTask.this.cancel();
                } finally {
                    System.out.println(Thread.currentThread().getName() + " super.cancel(mayInterruptIfRunning)");
                    return super.cancel(mayInterruptIfRunning);
                }
            }
        };
    }

    @Override
    public T call() throws Exception {
        System.out.println("call线程：" + Thread.currentThread().getName());
        try {
            InputStream in = socket.getInputStream();
            byte[] buf = new byte[1024];
            while (true) {
                int count = in.read(buf);
                if (count < 0) {
                    break;
                } else if (count > 0) {
                    System.out.println("size:" + count);
                    System.out.println(new String(buf));
                }
            }
        } catch (Exception e) {//允许线程退出
            System.out.println("退出任务");
        } finally {
            if (socket != null && !socket.isClosed()) {
                System.out.println(Thread.currentThread().getName() + " isConnected:" + socket.isConnected() +
                        " isClosed:" + socket.isClosed() + " isBound:" + socket.isBound());
                System.out.println(Thread.currentThread().getName() + " isInputShutdown:" + socket.isInputShutdown() +
                        " isOutputShutdown:" + socket.isOutputShutdown());
                socket.close();
            }
        }
        return (T) "任务结束";
    }
}
