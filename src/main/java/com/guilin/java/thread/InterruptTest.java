package com.guilin.java.thread;

/**
 * Created by guilin1 on 16/2/27.
 * 线程中断
 */
public class InterruptTest implements Runnable {

    public static void main(String[] args) throws Exception {

        StringBuffer buffer = new StringBuffer();
        String[] arr = "a,b".split(",");
        for (String s : arr) {
            buffer.append(s);
        }
        System.out.println(buffer);


        Thread thread = new Thread(new InterruptTest(), "My Thread");
        System.out.println("Starting thread...");

        thread.start();
        Thread.sleep(3000);
        System.out.println("Interrupting thread...");
        thread.interrupt();
        System.out.println("线程是否中断：" + thread.isInterrupted());
        Thread.sleep(3000);
        System.out.println("Stopping application...");
    }


    @Override
    public void run() {
        boolean stop = false;
        while (!stop) {
            System.out.println("My Thread is running...");
            // 让该循环持续一段时间，使上面的话打印次数少点
            long time = System.currentTimeMillis();
            while ((System.currentTimeMillis() - time < 1000)) {
            }
            if (Thread.currentThread().isInterrupted()) {
                stop = true;
//                return;
            }
        }
        System.out.println("My Thread exiting under request...");
    }
}
