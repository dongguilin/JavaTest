package com.guilin.java.thread.concurrent.producer_consumer;

import java.util.concurrent.*;

/**
 * Created by T57 on 2016/7/25 0025.
 * 生产者-消费者模式能够很好地对生产者和消费者线程进行解耦，优化了系统整体结构。同时，由于缓冲区的作用，
 * 允许生产者线程和消费者线程存在执行上的性能差异，从一定程度上缓解了性能瓶颈对系统性能的影响。
 */
public class Client {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<PCData> queue = new LinkedBlockingDeque<>(10);//建立缓冲区
        Producer producer1 = new Producer(queue);//建立生产者
        Producer producer2 = new Producer(queue);
        Producer producer3 = new Producer(queue);
        Consumer consumer1 = new Consumer(queue);//建立消费者
        Consumer consumer2 = new Consumer(queue);
        Consumer consumer3 = new Consumer(queue);

        ExecutorService service = Executors.newCachedThreadPool();//建立线程池
        service.execute(producer1);
        service.execute(producer2);
        service.execute(producer3);
        service.execute(consumer1);
        service.execute(consumer2);
        service.execute(consumer3);

        Thread.sleep(10 * 1000);

        producer1.stop();
        producer2.stop();
        producer3.stop();

        consumer1.stop();
        consumer2.stop();
        consumer3.stop();

        service.shutdown();
        System.out.println(service.awaitTermination(1, TimeUnit.MINUTES));
        ;
    }
}
