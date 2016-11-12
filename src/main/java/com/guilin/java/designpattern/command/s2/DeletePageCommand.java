package com.guilin.java.designpattern.command.s2;

/**
 * Created by T57 on 2016/11/12 0012.
 * 删除页面的命令
 */
public class DeletePageCommand extends Command {
    @Override
    public void execute() {
        super.pg.find();
        super.pg.delete();
        super.pg.plan();
    }
}
