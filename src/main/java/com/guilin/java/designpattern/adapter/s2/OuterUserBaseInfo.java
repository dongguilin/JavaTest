package com.guilin.java.designpattern.adapter.s2;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by T57 on 2016/9/12 0012.
 */
public class OuterUserBaseInfo implements IOuterUserBaseInfo<String, String> {
    @Override
    public Map<String, String> getUserBaseInfo() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", "22");
        return map;
    }
}
