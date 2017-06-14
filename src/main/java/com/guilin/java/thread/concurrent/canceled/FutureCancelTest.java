package com.guilin.java.thread.concurrent.canceled;

import java.util.concurrent.*;

/**
 * Created by guilin on 2017/6/10.
 * <p>
 * 通过Future来实现取消
 */
public class FutureCancelTest {

    private static final ExecutorService taskExec = Executors.newCachedThreadPool();

    static class Task implements Callable<String> {

        //创建Task类，指定实现Callable接口，并参数化为String类型。
        //实现call()方法，写入一条信息到控制台，并使这个线程在循环中睡眠100毫秒。
        @Override
        public String call() throws Exception {
            while (true) {
                System.out.println("我在执行任务: Test 来自" + Thread.currentThread().getName());
//                int i = 1 / 0;
//                List<String> lines = IOUtils.readLines(new FileReader(new File("a.txt")));
//                System.out.println(lines.size());

                Thread.sleep(100);
            }
        }
    }

    public static void main(String[] args) {
        try {
            timedRun(new Task(), 3, TimeUnit.SECONDS);
        } catch (RuntimeException e) {
            System.out.println("异常信息：" + e.getMessage());
        }

        taskExec.shutdown();
    }

    public static void timedRun(Callable callable, long timeout, TimeUnit unit) {
        Future<?> task = taskExec.submit(callable);
        try {
            task.get(timeout, unit);
        } catch (ExecutionException e) {
            //如果在任务中抛出了异常，那么重新抛出该异常
            System.out.println("任务异常");
            throw launderThrowable(e.getCause());
        } catch (TimeoutException e) {
            System.out.println("超时");
        } catch (InterruptedException e) {
            System.out.println("中断");
        } finally {
            System.out.println("执行取消任务");
            //如果任务已经结束，那么执行取消操作也不会带来任何影响
            task.cancel(true);//如果任务正在运行，那么将被中断
        }
    }

    public static RuntimeException launderThrowable(Throwable t) {
        if (t instanceof RuntimeException)
            return (RuntimeException) t;
        else if (t instanceof Error)
            throw (Error) t;
        else
            throw new IllegalStateException("Not unchecked", t);
    }
}
