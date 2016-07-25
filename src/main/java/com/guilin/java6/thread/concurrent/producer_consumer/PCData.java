package com.guilin.java6.thread.concurrent.producer_consumer;

/**
 * Created by T57 on 2016/7/24 0024.
 * 生产者与消费者之间共享数据的模型
 */
public class PCData {

    private final int intData;

    public PCData(int d) {
        intData = d;
    }

    public PCData(String d) {
        intData = Integer.valueOf(d);
    }

    public int getIntData() {
        return intData;
    }

    @Override
    public String toString() {
        return "PCData{" +
                "intData=" + intData +
                '}';
    }
}
