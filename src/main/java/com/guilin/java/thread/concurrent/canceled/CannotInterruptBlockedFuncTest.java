package com.guilin.java.thread.concurrent.canceled;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by guilin1 on 16/4/13.
 * <p/>
 * 不可取消的任务在退出前恢复中断
 */
public class CannotInterruptBlockedFuncTest {

    public Thread getCurrentThread() {
        return Thread.currentThread();
    }

    public String getNextTask(BlockingQueue<String> queue) {
        boolean interrupted = false;

        try {
            while (true) {
                try {
                    return queue.take();
                } catch (InterruptedException e) {
                    interrupted = true;
                    //重新尝试
                }
            }
        } finally {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        CannotInterruptBlockedFuncTest test = new CannotInterruptBlockedFuncTest();
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        test.getNextTask(queue);

        try {
            Thread.sleep(2000);
//            Thread.currentThread().interrupted();
            System.out.println(test.getCurrentThread());
            test.getCurrentThread().interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("finish");

    }


}
