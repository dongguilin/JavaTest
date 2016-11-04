package com.guilin.java.designpattern.adapter.s2;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by T57 on 2016/9/12 0012.
 */
public class OuterUserHomeInfo implements IOuterUserHomeInfo {
    @Override
    public Map getUserHomeInfo() {
        Map map = new HashMap();
        map.put("home", "河南");
        return map;
    }
}
