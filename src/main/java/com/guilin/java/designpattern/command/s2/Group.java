package com.guilin.java.designpattern.command.s2;

/**
 * Created by T57 on 2016/11/12 0012.
 * 抽象组
 */
public abstract class Group {
    //甲乙双方分开办公，如果你要和某个组讨论，你首先需要找到这个组
    public abstract void find();

    //被要求增加的功能
    public abstract void add();

    //被要求删除的功能
    public abstract void delete();

    //被要求修改的功能
    public abstract void change();

    //被要求给出所有的变更计划
    public abstract void plan();

}
