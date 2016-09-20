package com.guilin.java6.performance;

import java.io.*;

/**
 * Created by guilin on 2016/9/20.
 * pidstat -p [pid] -d -t 1 3
 * -d参数表明监控对象为磁盘I/O，1 3 表示每秒钟采样一次，合计采样3次
 * pidstat -p [pid] -r -t 1 3
 * -r参数表明监控对象为内存
 */
public class HoldIOMain {

    public static void main(String[] args) {
        new Thread(new HoldIOTask()).start();
        new Thread(new LazyTask()).start();
        new Thread(new LazyTask()).start();
        new Thread(new LazyTask()).start();
    }

    public static class HoldIOTask implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    FileOutputStream fos = new FileOutputStream(new File("E:/test/tmp"));
                    for (int i = 0; i < 10000; i++) {
                        fos.write(i);//大量的写操作
                    }
                    fos.close();
                    FileInputStream fis = new FileInputStream(new File("E:/test/tmp"));
                    while (fis.read() != -1) ;//大量的读操作
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
