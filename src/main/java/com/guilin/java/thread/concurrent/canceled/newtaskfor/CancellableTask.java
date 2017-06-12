package com.guilin.java.thread.concurrent.canceled.newtaskfor;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

/**
 * Created by guilin on 2017/6/11.
 * 可取消的任务接口
 */
public interface CancellableTask<T> extends Callable<T> {
    void cancel();

    RunnableFuture<T> newTask();
}
