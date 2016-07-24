package com.guilin.java6.thread.concurrent.master_worker;

/**
 * Created by T57 on 2016/7/24 0024.
 */
public class PlusWorker extends Worker {

    @Override
    public Object handle(Object input) {
        Integer i = (Integer) input;
        return i * i * i;
    }
}
