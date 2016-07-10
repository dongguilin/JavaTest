package com.guilin.java6.thread.concurrent;

import java.util.Date;

/**
 * Created by T57 on 2016/7/10 0010.
 */
public class ThrealLocalTest implements Runnable {

    public static void main(String[] args) {
        new ThrealLocalTest(System.currentTimeMillis()).run();
        new ThrealLocalTest(System.currentTimeMillis()).run();
    }

    public static final ThreadLocal<Date> localvar = new ThreadLocal<>();

    private long time;

    public ThrealLocalTest(long time) {
        this.time = time;
    }

    @Override
    public void run() {
        Date d = new Date(time);
        for (int i = 0; i < 50000; i++) {
            System.out.println(i);
            localvar.set(d);
            if (localvar.get().getTime() != time) {
                //不会输出
                System.out.println("id=" + time + " localvar=" + localvar.get().getTime());
            }
        }
    }
}
