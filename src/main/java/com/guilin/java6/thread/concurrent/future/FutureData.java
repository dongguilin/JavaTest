package com.guilin.java6.thread.concurrent.future;

/**
 * Created by T57 on 2016/7/18 0018.
 * FutureData是RealData的包装
 */
public class FutureData implements Data {

    protected RealData realData = null;

    protected boolean isReady = false;//是否可以获取真实数据

    public synchronized void setRealData(RealData realData) {
        if (isReady) {
            return;
        }
        this.realData = realData;
        isReady = true;
        notifyAll();
    }

    @Override
    public synchronized String getResult() {//会阻塞等待RealData构造完成
        while (!isReady) {
            try {
                System.out.println("wait");
                wait();//一直阻塞等待，直到RealData被注入
            } catch (InterruptedException e) {
            }
        }
        return realData.getResult();
    }
}
