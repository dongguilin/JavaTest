package com.guilin.java.collection;

import org.junit.Test;

import java.awt.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by T57 on 2016/9/8 0008.
 */
public class ShallowCopyMap {

    @Test
    public void test1() {
        Map<String, Point> map = new HashMap<>();
        map.put("hello", new Point(11, 12));

        Map<String, Point> copyMap1 = new HashMap<>(map);//浅拷贝
        Map<String, Point> copyMap2 = Collections.synchronizedMap(new HashMap<String, Point>(map));
        Map<String, Point> copyMap3 = Collections.unmodifiableMap(new HashMap<String, Point>(map));
        Map<String, Point> copyMap4 = new LinkedHashMap<>(map);

        map.put("aa", new Point(22, 23));
        map.get("hello").setLocation(111, 121);

        System.out.println(map);//{aa=java.awt.Point[x=22,y=23], hello=java.awt.Point[x=111,y=121]}
        System.out.println(copyMap1);//{hello=java.awt.Point[x=111,y=121]}
        System.out.println(copyMap2);//{hello=java.awt.Point[x=111,y=121]}
        System.out.println(copyMap3);//{hello=java.awt.Point[x=111,y=121]}
        System.out.println(copyMap4);//{hello=java.awt.Point[x=111,y=121]}
    }
}
