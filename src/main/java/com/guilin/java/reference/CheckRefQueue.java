package com.guilin.java.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

/**
 * Created by guilin on 2016/9/9.
 */
public class CheckRefQueue extends Thread {

    private ReferenceQueue<MyObject> queue;

    public CheckRefQueue(ReferenceQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            Reference<MyObject> obj = null;
            try {
                obj = (Reference<MyObject>) queue.remove();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (obj != null) {
                System.out.println("Object for SoftReference is " + obj.get());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
