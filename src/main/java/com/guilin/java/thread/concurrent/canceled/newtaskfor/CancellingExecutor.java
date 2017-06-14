package com.guilin.java.thread.concurrent.canceled.newtaskfor;

import java.util.concurrent.*;

/**
 * Created by guilin on 2017/6/11.
 * 通过newTaskFor将非标准的取消操作封装在任务中
 */
public class CancellingExecutor extends ThreadPoolExecutor {
    public CancellingExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        if (callable instanceof CancellableTask) {
            return ((CancellableTask) callable).newTask();
        } else {
            return super.newTaskFor(callable);
        }
    }
}
