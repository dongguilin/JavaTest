package com.guilin.java.designpattern.memo.multimemo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by T57 on 2016/9/9 0009.
 * 备忘录管理者
 */
public class Caretaker {

    private Map<String, IMemo> memoMap = new HashMap<>();

    public IMemo getMemo(String id) {
        return memoMap.get(id);
    }

    public void setMemo(String id, IMemo memo) {
        memoMap.put(id, memo);
    }
}
