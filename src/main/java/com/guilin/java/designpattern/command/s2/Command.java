package com.guilin.java.designpattern.command.s2;

/**
 * Created by T57 on 2016/11/12 0012.
 * 抽象命令类
 */
public abstract class Command {
    //把三个组都定义好，子类可以直接使用
    protected RequirementGroup rg = new RequirementGroup();

    //美工组
    protected PageGroup pg = new PageGroup();

    //代码组
    protected CodeGroup cg = new CodeGroup();

    public abstract void execute();
}
