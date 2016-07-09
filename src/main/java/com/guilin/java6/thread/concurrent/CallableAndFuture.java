package com.guilin.java6.thread.concurrent;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by guilin on 2014/6/7.
 * 提交任务、异步执行、阻塞拿到结果
 */
public class CallableAndFuture {

    public static void main(String[] args) throws Exception {
        ExecutorService threadPool1 = Executors.newSingleThreadExecutor();
        //有返回值的任务
        Future<String> future = threadPool1.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(5000);
                return "hello";
            }
        });
        System.out.println("等待结果");
        System.out.println("拿到结果：" + future.get());//阻塞等待
        threadPool1.shutdownNow();

        ExecutorService threadPool2 = Executors.newFixedThreadPool(10);
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadPool2);
        for (int i = 1; i <= 10; i++) {
            final int seq = i;
            completionService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    Thread.sleep(new Random().nextInt(10000));
                    System.out.println(Thread.currentThread().getName());
                    return seq;
                }
            });
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(completionService.take().get());//按执行完的先后顺序拿到结果，与提交顺序无关
        }

        threadPool2.shutdown();

    }
}
