package com.guilin.java.designpattern.memo.multimemo;

import java.util.Map;

/**
 * Created by T57 on 2016/9/9 0009.
 * 发起人
 */
public class Originator {

    private String state1;

    private String state2;

    private String state3;

    //创建一个备忘录
    public IMemo createMemo() {
        return new Memo(BeanUtils.backProp(this));
    }

    //恢复一个备忘录
    public void restoreMemo(IMemo memo) {
        BeanUtils.restoreProp(this, ((Memo) memo).getStateMap());
    }

    //备份是不能补篡改的，缩小权限，保证只能是发起人可读
    private class Memo implements IMemo {
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

    @Override
    public String toString() {
        return "Originator{" +
                "state1='" + state1 + '\'' +
                ", state2='" + state2 + '\'' +
                ", state3='" + state3 + '\'' +
                '}';
    }

    public String getState1() {
        return state1;
    }

    public void setState1(String state1) {
        this.state1 = state1;
    }

    public String getState2() {
        return state2;
    }

    public void setState2(String state2) {
        this.state2 = state2;
    }

    public String getState3() {
        return state3;
    }

    public void setState3(String state3) {
        this.state3 = state3;
    }
}
