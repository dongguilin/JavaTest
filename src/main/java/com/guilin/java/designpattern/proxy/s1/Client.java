package com.guilin.java.designpattern.proxy.s1;

/**
 * Created by T57 on 2016/10/31 0031.
 */
public class Client {

    public static void main(String[] args) {
        IGamePlayer player = new GamePlayer("张三");

        IGamePlayer proxy = new GamePlayerProxy(player);

        proxy.login("zhangsan", "123456");
        proxy.killBoss();
        proxy.upgrade();
    }
}
