package com.guilin.java6.thread.concurrent.guarded_suspension;

/**
 * Created by T57 on 2016/7/24 0024.
 * 并行程序的保护暂停模式
 */
public class Client {

    public static void main(String[] args) {
        RequestQueue requestQueue = new RequestQueue();
        for (int i = 0; i < 10; i++) {
            new ServerThread(requestQueue, "ServerThread" + i).start();
        }
        for (int i = 0; i < 10; i++) {
            new ClientThread(requestQueue, "ClientThread" + i).start();
        }
    }
}
