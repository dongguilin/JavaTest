package com.guilin.java6.thread;

public class ThreadCommunication {

    public static void main(String[] args) {
        final Business business = new Business();
        // 从线程
        new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 1; i <= 50; i++) {
                    try {
                        business.sub(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        // 主线程
        for (int i = 1; i <= 50; i++) {
            try {
                business.main(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class Business {

    private boolean isSubThread = true;

    public synchronized void sub(int i) throws InterruptedException {
        while (!isSubThread) {
            this.wait();
        }
        for (int j = 1; j <= 10; j++) {
            System.out.println("sub thread " + Thread.currentThread().getName()
                    + ":sequence of " + j + " loop " + i);

        }
        isSubThread = false;
        this.notify();
    }

    public synchronized void main(int i) throws InterruptedException {
        while (isSubThread) {
            this.wait();
        }
        for (int j = 1; j <= 10; j++) {
            System.out
                    .println("main thread " + Thread.currentThread().getName()
                            + ":sequence of " + j + " loop " + i);

        }
        isSubThread = true;
        this.notify();
    }

}
