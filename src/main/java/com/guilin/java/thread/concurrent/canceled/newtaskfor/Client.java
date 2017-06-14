package com.guilin.java.thread.concurrent.canceled.newtaskfor;

import org.junit.Test;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * Created by guilin on 2017/6/11.
 */
public class Client {

    @Test
    public void test1() {
        System.out.println("主线程：" + Thread.currentThread().getName());
        SocketUsingTask<String> task = new SocketUsingTask<>();
        try {
            task.setSocket(new Socket("192.168.137.129", 9999));
        } catch (IOException e) {
            System.exit(1);
        }
        ExecutorService taskExec = Executors.newCachedThreadPool();
        RunnableFuture<String> futureTask = task.newTask();
        taskExec.execute(futureTask);

        //另一任务
        taskExec.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "...");
                }
            }
        });

        try {
            System.out.println("结果：" + futureTask.get(2, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            System.out.println("中断");
        } catch (ExecutionException e) {
            System.out.println("执行异常:" + e.getMessage());
        } catch (TimeoutException e) {
            System.out.println("执行超时");
            System.out.println("开始取消任务");
            futureTask.cancel(true);

            try {
                Thread.sleep(2000);
                System.out.println("结果：" + futureTask.get());
            } catch (Exception e1) {
            }
        }

        try {
            taskExec.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(1000);
        CancellingExecutor executor = new CancellingExecutor(2, 5, 1, TimeUnit.DAYS, blockingQueue);

        SocketUsingTask<String> task = new SocketUsingTask<>();
        try {
            task.setSocket(new Socket("192.168.137.129", 9999));
        } catch (IOException e) {
            System.exit(1);
        }

        //另一任务
        executor.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "...");
                }
            }
        });

        RunnableFuture<String> runnableFuture = executor.newTaskFor(task);

        executor.execute(runnableFuture);

        try {
            System.out.println("结果：" + runnableFuture.get(10, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            System.out.println("中断");
        } catch (ExecutionException e) {
            System.out.println("执行异常:" + e.getMessage());
        } catch (TimeoutException e) {
            System.out.println("执行超时");
            System.out.println("开始取消任务");
            runnableFuture.cancel(true);

//            try {
//                Thread.sleep(2000);
//                System.out.println("结果：" + runnableFuture.get());
//            } catch (Exception e1) {
//                e.printStackTrace();
//            }
        }

        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
