package com.guilin.java.designpattern.memo.multistate;

import java.util.Map;

/**
 * Created by T57 on 2016/9/9 0009.
 * 备忘录角色
 */
public class Memo {

    private Map<String, Object> stateMap;

    public Memo(Map<String, Object> stateMap) {
        this.stateMap = stateMap;
    }

    public Map<String, Object> getStateMap() {
        return stateMap;
    }

    public void setStateMap(Map<String, Object> stateMap) {
        this.stateMap = stateMap;
    }
}
