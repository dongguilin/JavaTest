package com.guilin.java6.thread.vehicletracker;

/**
 * Created by T57 on 2016/9/8 0008.
 * 不可变，线程安全
 */
public class Point {

    private final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
