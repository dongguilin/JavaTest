package com.guilin.java.designpattern.proxy.s3;

import com.guilin.java.designpattern.proxy.s1.GamePlayer;
import com.guilin.java.designpattern.proxy.s1.IGamePlayer;

import java.lang.reflect.Proxy;

/**
 * Created by T57 on 2016/11/7 0007.
 */
public class Client {

    public static void main(String[] args) {
        //定义一个玩家
        IGamePlayer player = new GamePlayer("张三");
        //动态产生一个代理类
        IGamePlayer proxy = (IGamePlayer) Proxy.newProxyInstance(player.getClass().getClassLoader(), new Class[]{IGamePlayer.class}, new GamePlayIH(player));
        proxy.killBoss();
        proxy.upgrade();

    }
}
