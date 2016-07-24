package com.guilin.java6.thread.concurrent.master_worker;

import java.util.Map;
import java.util.Set;

/**
 * Created by T57 on 2016/7/24 0024.
 * 并行程序的Master-worker模式
 */
public class Client {

    public static void main(String[] args) {
        Master m = new Master(new PlusWorker(), 5);//固定使用5个worker
        for (int i = 0; i < 100; i++) {
            m.submit(i);//提交100个子任务
        }
        m.execute();//开始计算
        int re = 0;//最终计算结果
        Map<String, Object> resultMap = m.getResultMap();

        int num = 0;

        while (resultMap.size() > 0 || !m.isComplete()) {//不需要等待所有worker都执行完，即可
            Set<String> keys = resultMap.keySet();//开始计算最终结果
            System.out.println(keys.size());
            for (String key : keys) {
                int i = (int) resultMap.get(key);
                re += i;
                resultMap.remove(key);

                num++;
            }
        }
        System.out.println("num:" + num);
        System.out.println("最终结果：" + re);
    }
}
