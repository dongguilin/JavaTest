package com.guilin.java.thread.concurrent.canceled;

/**
 * Created by hadoop on 2016/2/28.
 */
public class InterruptTest {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().isInterrupted());//false

        Thread.currentThread().interrupt();
        System.out.println(Thread.currentThread().isInterrupted());//true

        System.out.println(Thread.interrupted());//true,测试当前线程是否已经中断，并清除线程的中断状态
        System.out.println(Thread.currentThread().isInterrupted());//false

        Thread.currentThread().interrupt();
        System.out.println(Thread.currentThread().isInterrupted());//true

    }
}
