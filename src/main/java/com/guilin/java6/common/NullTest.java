package com.guilin.java6.common;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by guilin1 on 16/2/29.
 */
public class NullTest {

    public static void main(String[] args) {
        String str = null;
        System.out.println(StringUtils.isEmpty(str) + "," + StringUtils.isBlank(str));//true,true
        str = "";
        System.out.println(StringUtils.isEmpty(str) + "," + StringUtils.isBlank(str));//true,true
        str = " ";
        System.out.println(StringUtils.isEmpty(str) + "," + StringUtils.isBlank(str));//false,true
        str = "null";
        System.out.println(StringUtils.isEmpty(str) + "," + StringUtils.isBlank(str));//false,false
        str = "Null";
        System.out.println(StringUtils.isEmpty(str) + "," + StringUtils.isBlank(str));//false,false

        System.out.println("--------------------");

        Map<String, String> map = null;
        System.out.println(MapUtils.isEmpty(map));//true
        map = new HashMap<>();
        System.out.println(MapUtils.isEmpty(map));//true
        map.put(null, "");
        System.out.println(MapUtils.isEmpty(map));//false
        map.put(null, " ");
        System.out.println(MapUtils.isEmpty(map));//false
        map.put(null, "a");
        System.out.println(MapUtils.isEmpty(map));//false
        map.put("a", null);
        System.out.println(MapUtils.isEmpty(map));//false
        map.put("b", "c");
        System.out.println(MapUtils.isEmpty(map));//false

        System.out.println("--------------------");
        List<String> list = null;
        System.out.println(CollectionUtils.isEmpty(list));//true
        list = new ArrayList<>();
        System.out.println(CollectionUtils.isEmpty(list));//true
        list.add("");
        System.out.println(CollectionUtils.isEmpty(list));//false

        System.out.println(new DateTime("2016-02-27"));

        System.out.println(new DateTime("2016-02-27").withTimeAtStartOfDay());

        System.out.println(new DateTime("2016-02-27").toString("yyyy-MM-dd HH:mm:ss"));


    }
}
