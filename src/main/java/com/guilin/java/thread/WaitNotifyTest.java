package com.guilin.java.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WaitNotifyTest {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {
            lock.lock();
            try{
                System.out.println("线程1");
                condition.await();
                System.out.println("被唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

        new Thread(()->{
            lock.lock();
            try{
                System.out.println("线程2");
                condition.signal();
                Thread.sleep(2000);
                System.out.println("通知");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("释放锁");
                lock.unlock();
            }

        }).start();


    }
}
