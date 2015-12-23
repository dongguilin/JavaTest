package com.guilin.java6.thread.webserver;

import java.util.concurrent.Executor;

/**
 * Created by hadoop on 2015/12/20.
 * 行为类似单线程的行为，即以同步的方式执行每个任务
 */
public class WithinThreadExecutor5 implements Executor {
    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
