package com.guilin.java6.designpattern.builder.s1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by T57 on 2016/10/13 0013.
 * 车辆模型的抽象类
 */
public abstract class CarModel {
    private List<String> sequence = new ArrayList<>();

    protected abstract void start();

    protected abstract void stop();

    protected abstract void alarm();

    protected abstract void engineBoom();

    final public void run() {
        for (int i = 0; i < sequence.size(); i++) {
            String actionName = sequence.get(i);
            switch (actionName) {
                case "start":
                    start();
                    break;
                case "stop":
                    stop();
                    break;
                case "alarm":
                    alarm();
                    break;
                case "engineBoom":
                    engineBoom();
                    break;
            }
        }
    }

    final public void setSequence(List<String> sequence) {
        this.sequence = sequence;
    }

}
