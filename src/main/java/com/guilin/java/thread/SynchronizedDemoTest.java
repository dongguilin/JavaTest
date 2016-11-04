package com.guilin.java.thread;

/**
 * Created by hadoop on 2015/12/22.
 */
public class SynchronizedDemoTest {

    public static void main(String[] args) {

        final SynchronizedDemo demo = new SynchronizedDemo();
        final SynchronizedDemo demo2 = new SynchronizedDemo();

        for (int i = 0; i < 4; i++) {
            final int finalI = i;
            System.out.println(finalI);
            new Thread() {
                @Override
                public void run() {
                    try {
                        if (finalI % 2 == 0) {
                            demo.foo3();
                            demo2.foo4();
                        } else {
                            demo.foo4();
                            demo2.foo3();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }


    }


}
