package com.guilin.java.thread.webserver;

import java.util.concurrent.Executor;

/**
 * Created by hadoop on 2015/12/20.
 * 为每个请求启动一个新线程的Executor
 */
public class ThreadPerTaskExecutor4 implements Executor {
    @Override
    public void execute(Runnable command) {
        new Thread(command).start();
    }
}
