package com.guilin.java.thread;

public class MultiThreadShareData {

    private int count;

    public synchronized void increment() {
        count++;
        System.out.println(count + "++");
    }

    public synchronized void decrement() {
        count--;
        System.out.println(count + "--");
    }

    class Increment implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                increment();
            }
        }
    }

    class Decrement implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                decrement();
            }
        }
    }

    public static void main(String[] args) {
        MultiThreadShareData test = new MultiThreadShareData();
        Increment inc = test.new Increment();
        Decrement dec = test.new Decrement();
        for (int i = 0; i < 2; i++) {
            new Thread(inc).start();
            new Thread(dec).start();
        }
    }

}
