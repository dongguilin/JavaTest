package com.guilin.java6.thread.vehicletracker;

import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 * Created by T57 on 2016/9/8 0008.
 */
@Immutable
public class Point {

    private final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
