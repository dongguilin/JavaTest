package com.guilin.java.performance;

/**
 * Created by guilin on 2016/9/20.
 * CPU使用率监控
 * 在linux上使用pidstat工具监控程序CPU使用率
 * jps
 * pidstat -p [pid] 1 3 -u -t
 * -u参数表示对CPU使用率的监控
 * -t参数将系统性能的监控细化到线程级别
 * jstack -l [pid] > /tmp/t.txt 导出指定java程序的所有线程
 */
public class HoldCPUMain {

    public static void main(String[] args) {
        new Thread(new HoldCPUTask()).start();
        new Thread(new LazyTask()).start();
        new Thread(new LazyTask()).start();
        new Thread(new LazyTask()).start();
    }

    public static class HoldCPUTask implements Runnable {

        @Override
        public void run() {
            while (true) {
                double a = Math.random() * Math.random();//占用CPU
            }
        }
    }

    public static class LazyTask implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
            }
        }
    }

}
