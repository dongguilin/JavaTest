package com.guilin.java6.performance;

import org.apache.commons.collections.map.HashedMap;

import java.util.Map;

/**
 * Created by guilin on 2016/9/8.
 */
public class ConfigData {

    private static Map<String, Object> data;

    private int num = 0;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    static {
        data = new HashedMap();
        data.put("hello", "world");
    }

    public static Map<String, Object> getData() {
        return data;
    }

}
