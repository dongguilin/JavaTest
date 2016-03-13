package com.guilin.java6.lang;

import org.junit.Test;

/**
 * Created by hadoop on 2016/3/13.
 */
public class RuntimeDemo {

    @Test
    public void test1() {
        System.out.println("可用处理器数目：" + Runtime.getRuntime().availableProcessors());
        System.out.println("Java 虚拟机中的空闲内存量：" + Runtime.getRuntime().freeMemory() / 1000.0 / 1024);
        System.out.println("Java 虚拟机试图使用的最大内存量：" + Runtime.getRuntime().maxMemory() / 1000 / .1024);
        System.out.println("Java 虚拟机中的内存总量：" + Runtime.getRuntime().totalMemory() / 1000.0 / 1024);
    }

    @Test
    public void test2() throws InterruptedException {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("关闭前的清理工作1...");
            }
        });

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("关闭前的清理工作2...");
            }
        });

        Thread.sleep(2000);

        System.out.println("启动关闭序列");
        System.exit(0);

        //不会执行
        System.out.println("关闭");

        //此方法不会启动关闭钩子，并且如果已启用退出终结，此方法也不会运行未调用的终结方法。如果已经发起关闭序列，那么此方法不会等待所有正在运行的关闭钩子或终结方法完成其工作
//        Runtime.getRuntime().halt(0);

    }
}
