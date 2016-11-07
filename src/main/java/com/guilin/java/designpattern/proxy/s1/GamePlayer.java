package com.guilin.java.designpattern.proxy.s1;

/**
 * Created by T57 on 2016/10/31 0031.
 */
public class GamePlayer implements IGamePlayer {

    private String name;

    public GamePlayer(String name) {
        this.name = name;
    }

    @Override
    public void login(String user, String password) {
        System.out.println("user:" + user + ", password:" + password);
    }

    @Override
    public void killBoss() {
        System.out.println(name + " 在打怪");
    }

    @Override
    public void upgrade() {
        System.out.println(name + " 又升了一级");
    }
}
