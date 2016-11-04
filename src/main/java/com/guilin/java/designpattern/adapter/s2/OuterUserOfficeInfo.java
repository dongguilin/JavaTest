package com.guilin.java.designpattern.adapter.s2;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by T57 on 2016/9/12 0012.
 */
public class OuterUserOfficeInfo implements IOuterUserOfficeInfo {
    @Override
    public Map getUserOfficeInfo() {
        Map map = new HashMap();
        map.put("position", "boss");
        return map;
    }
}
