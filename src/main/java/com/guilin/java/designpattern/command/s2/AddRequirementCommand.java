package com.guilin.java.designpattern.command.s2;

/**
 * Created by T57 on 2016/11/12 0012.
 * 增加需求的命令
 */
public class AddRequirementCommand extends Command {
    @Override
    public void execute() {
        super.rg.find();
        super.rg.add();
        super.rg.plan();
    }
}
