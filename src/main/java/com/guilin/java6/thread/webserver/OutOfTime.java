package com.guilin.java6.thread.webserver;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hadoop on 2015/12/20.
 * 错误的Timer行为
 */
public class OutOfTime {

    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();
        timer.schedule(new ThrowTask(), 1);
        Thread.sleep(1);
        timer.schedule(new ThrowTask(), 1);
        Thread.sleep(5);
        System.out.println("end");
    }

    private static class ThrowTask extends TimerTask {
        @Override
        public void run() {
            throw new RuntimeException();
        }
    }
}
