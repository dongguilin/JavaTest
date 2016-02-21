package com.guilin.java6.thread;

/**
 * Created by hadoop on 2015/9/26.
 */
public class YieldTest {

    public static void main(String[] args) {
        ThreadChild t1 = new ThreadChild("one");
        t1.run();
        ThreadChild t2 = new ThreadChild("two");
        t2.run();
    }
}

class ThreadChild extends Thread {
    private String sname = "";

    ThreadChild(String s) {
        sname = s;
    }

    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {
            try {
                sleep(1000);
            } catch (Exception e) {

            }
            yield();//暂停当前正在执行的线程对象，并执行其他线程
            System.out.println(sname);
        }
    }
}
