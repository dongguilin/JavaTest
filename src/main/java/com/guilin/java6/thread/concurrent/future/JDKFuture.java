package com.guilin.java6.thread.concurrent.future;

import java.util.concurrent.*;

/**
 * Created by T57 on 2016/7/18 0018.
 */
public class JDKFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> future = new FutureTask<String>(new RealData("a"));

        ExecutorService pool = Executors.newFixedThreadPool(1);
        pool.execute(future);

        System.out.println("请求完毕");

        try {
            //这里可以用一个sleep代替对其他业务的逻辑的处理
            //在处理这些业务逻辑的过程中，RealData被创建，从而充分利用了等待时间
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }

        System.out.println("数据=" + future.get());

        pool.shutdown();
        boolean flag = pool.awaitTermination(30, TimeUnit.SECONDS);
        System.out.println(flag);

    }

    private static class RealData implements Callable<String> {

        private String para;

        public RealData(String para) {
            this.para = para;
        }

        @Override
        public String call() throws Exception {
            //这里是真实的业务逻辑，其执行可能很慢
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 10; i++) {
                sb.append(para);
                try {
                    //使用sleep，代替一个很慢的操作过程
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
            return sb.toString();
        }
    }
}
