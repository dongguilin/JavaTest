package com.guilin.java.designpattern.memo.multistate;

/**
 * Created by T57 on 2016/9/9 0009.
 * 备忘录管理员、看管者角色
 */
public class Caretaker {

    private Memo memo;

    public Memo getMemo() {
        return memo;
    }

    public void setMemo(Memo memo) {
        this.memo = memo;
    }
}
