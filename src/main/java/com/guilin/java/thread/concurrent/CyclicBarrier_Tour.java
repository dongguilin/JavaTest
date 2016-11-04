package com.guilin.java.thread.concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: hadoop
 * Date: 15-3-4
 * Time: 下午8:11
 * To change this template use File | Settings | File Templates.
 * <p/>
 * 三个旅行团分别以自驾游、旅游大巴、徒步的方式去Shenzhen, Guangzhou, Shaoguan, Changsha, Wuhan旅游，
 * 三个团同时都到一个地方后才能去下一个旅游景点
 */
public class CyclicBarrier_Tour {

    // 徒步需要的时间: Shenzhen, Guangzhou, Shaoguan, Changsha, Wuhan
    private static int[] timeWalk = {5, 8, 15, 15, 10};
    // 自驾游
    private static int[] timeSelf = {1, 3, 4, 4, 5};
    // 旅游大巴
    private static int[] timeBus = {2, 4, 6, 6, 7};

    static String now() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date()) + ": ";
    }

    //旅游
    static class Tour implements Runnable {
        private int[] times;
        private CyclicBarrier barrier;
        private String tourName;

        public Tour(CyclicBarrier barrier, String tourName, int[] times) {
            this.times = times;
            this.tourName = tourName;
            this.barrier = barrier;
        }

        public void run() {
            try {
                Thread.sleep(times[0] * 1000);
                System.out.println(now() + tourName + " arrived Shenzhen");
                barrier.await();
                Thread.sleep(times[1] * 1000);
                System.out.println(now() + tourName + " arrived Guangzhou");
                barrier.await();
                Thread.sleep(times[2] * 1000);
                System.out.println(now() + tourName + " arrived Shaoguan");
                barrier.await();
                Thread.sleep(times[3] * 1000);
                System.out.println(now() + tourName + " arrived Changsha");
                barrier.await();
                Thread.sleep(times[4] * 1000);
                System.out.println(now() + tourName + " arrived Wuhan");
                barrier.await();
            } catch (InterruptedException e) {
            } catch (BrokenBarrierException e) {
            }
        }
    }

    public static void main(String[] args) {
        // 三个旅行团
        CyclicBarrier barrier = new CyclicBarrier(3);
        ExecutorService exec = Executors.newFixedThreadPool(3);
        exec.submit(new Tour(barrier, "WalkTour", timeWalk));
        exec.submit(new Tour(barrier, "SelfTour", timeSelf));
        exec.submit(new Tour(barrier, "BusTour", timeBus));
        exec.shutdown();
    }

}
