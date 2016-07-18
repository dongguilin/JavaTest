package com.guilin.java6.thread.concurrent.future;

/**
 * Created by T57 on 2016/7/18 0018.
 */
public class Client {

    public static void main(String[] args) {
        Client client = new Client();
        //这里会立即返回，因为得到的是FutureData，而不是RealData
        Data data = client.request("name");
        System.out.println("请求完毕");

        try {
            //这里可以用一个sleep代替对其他业务的逻辑的处理
            //在处理这些业务逻辑的过程中，RealData被创建，从而充分利用了等待时间
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }

        //使用真实数据
        System.out.println("数据=" + data.getResult());
    }

    public Data request(final String queryStr) {
        final FutureData future = new FutureData();

        //RealData的构造很慢，所以在单独的线程中运行
        new Thread() {
            @Override
            public void run() {
                RealData realData = new RealData(queryStr);
                future.setRealData(realData);
            }
        }.start();

        return future;
    }
}
