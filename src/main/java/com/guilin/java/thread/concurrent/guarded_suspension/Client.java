package com.guilin.java.thread.concurrent.guarded_suspension;

/**
 * Created by T57 on 2016/7/24 0024.
 * 并行程序的保护暂停模式
 * Guarded Suspension模式可以在一定程度上缓解系统压力，它可以将系统的负载在时间轴上均匀地分配，
 * 使用该模式后，可以有效降低系统的瞬时负载，对提高系统的按压力和稳定性有一定的帮助。
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
