package com.guilin.java6.designpattern.memo.clone;

/**
 * Created by T57 on 2016/9/9 0009.
 * clone方式的备忘录
 * 发起人自主备份和恢复
 */
public class Originator implements Cloneable {

    //内部状态
    private String state;

    private Originator backup;

    //创建一个备忘录
    public void createMemo() {
        this.backup = this.clone();
    }

    //恢复一个备忘录
    public void restoreMemo() {
        this.setState(this.backup.getState());
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    //克隆当前对象
    @Override
    protected Originator clone() {
        try {
            return (Originator) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
