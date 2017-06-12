package com.guilin.java.thread.concurrent.canceled;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created by guilin on 2017/6/11.
 * 通过改写interrupt方法将非标准的取消操作封装在Thread中
 */
public class ReaderThread extends Thread {
    private final Socket socket;
    private final InputStream in;

    public ReaderThread(Socket socket) throws IOException {
        this.socket = socket;
        this.in = socket.getInputStream();
    }

    public static void main(String[] args) throws IOException {
        ReaderThread readerThread = new ReaderThread(new Socket("192.168.137.129", 9999));
        readerThread.start();
        try {
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("开始取消任务");
        readerThread.interrupt();
    }

    @Override
    public void interrupt() {
        try {
            socket.close();
        } catch (IOException ignored) {
        } finally {
            super.interrupt();
        }
    }

    @Override
    public void run() {
        try {
            byte[] buf = new byte[1024];
            while (true) {
                int count = in.read(buf);
                if (count < 0) {
                    break;
                } else if (count > 0) {
                    processBuffer(buf, count);
                }
            }
        } catch (Exception e) {//允许线程退出
            System.out.println("退出任务");
        }
    }

    private void processBuffer(byte[] buf, int count) {
        System.out.println("size:" + count);
        System.out.println(new String(buf));
    }
}
