package com.guilin.java.thread.concurrent.canceled;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by guilin1 on 16/4/13.
 * <p/>
 * 可中断的阻塞函数响应中断处理
 * <p/>
 * <p/>
 * 当调用可中断的阻塞函数时，例如Thread.sleep或BlockingQueue.put等，有两种实用的策略可用于处理InterruptedException
 * 1.传递异常，从而使你的方法也成为可中断的阻塞方法
 * <code>
 * BlockingQueue<Task> queue;
 * public Task getNextTask() throws InterruptedException{
 * return queue.take();
 * }
 * </code>
 * 2.恢复中断状态，从而使调用栈的上层代码能够对其进行处理
 * <p/>
 * 只有实现了线程中断策略的代码才可以屏蔽中断请求。在常规的任务和库代码中都不应该屏蔽中断请求。
 */
public class CanInterruptBlockedFuncTest {

    static class MyRun implements Runnable {

        private BlockingQueue<String> queue;

        public MyRun(BlockingQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {

            //仅用于测试
            for (int i = 0; i < 15; i++) {
                try {
                    //在多数可中断的阻塞方法都会在入口处检查中断状态，并且当发现该状态已被设置时会立即抛出InterruptedException
                    //通常，可中断的方法会在阻塞或进行重要的工作之前首先检查中断，从而尽快地响应中断
                    String str = queue.take();
//                    String str = queue.poll(1, TimeUnit.SECONDS);
                    System.out.println("take " + str);
                } catch (InterruptedException e) {
                    //中断异常被捕获后，状态已经清除
                    System.out.println(i + " " + Thread.currentThread().getName() + "," + Thread.currentThread().isInterrupted());

                    //两种实用的策略用于处理InterruptedException:1.传递异常 2.恢复中断状态
                    //因run方法无法抛出检查异常，因此采取第二种策略
                    //恢复中断状态
                    Thread.currentThread().interrupt();

                    System.out.println(i + " " + Thread.currentThread().getName() + "," + Thread.currentThread().isInterrupted());
                    e.printStackTrace();

                }
            }


            //按正常逻辑可这样处理
//            for (int i = 0; i < 15; i++) {
//                try {
//                    String str = queue.poll(1, TimeUnit.SECONDS);
//                    System.out.println("take " + str);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                    break;
//                }
//            }


        }

    }

    public static void main(String[] args) {

        BlockingQueue<String> queue = new LinkedBlockingDeque<>();
        try {
            queue.put("a");
            queue.put("b");
            queue.put("c");
            queue.put("d");
            queue.put("e");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Thread thread = new Thread(new MyRun(queue));

            thread.start();
            Thread.sleep(1000);
            thread.interrupt();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "," + Thread.currentThread().isInterrupted());
            e.printStackTrace();
        }


    }

}
