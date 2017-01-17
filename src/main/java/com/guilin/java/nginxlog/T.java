package com.guilin.java.nginxlog;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by T57 on 2017/1/17 0017.
 */
public class T {

    @Test
    public void test1() {
        Map<String, TimeCount> map = new HashMap<>();
        Map<String, TimeCount> map2 = new HashMap<>();
        map.put("a", new TimeCount("a"));
        map2.put("b", map.get("a"));
        map.clear();
        System.out.println(map2);

    }

    public static void compare(Map<String, TimeCount> map, Map<String, TimeCount> tmpMap) {
        if (map.size() == 0) {
            map.putAll(tmpMap);
            return;
        }

        for (String url : tmpMap.keySet()) {
            TimeCount timeCount = map.get(url);
            if (timeCount != null) {
                if (timeCount.getCount() < tmpMap.get(url).getCount()) {
                    map.put(url, tmpMap.get(url));
                }
            } else {
                map.put(url, tmpMap.get(url));
            }
        }

    }


    public static void main(String[] args) throws Exception {

        String regex = "(?<remoteAddr>\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}) - ((\\s+)|-) \\[(?<timeLocal>.*)\\] \"(?<requestMethod>POST|GET) (?<requestUrl>[^ ]+) ([^\"]*)\" (?<status>\\d+)";
        Pattern pattern = Pattern.compile(regex);

        File file = new File("d:/53elewebapp.log");

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = null;
        String time = "";
        Map<String, TimeCount> map = new HashMap();
        Map<String, TimeCount> tmpMap = new HashMap<>();
        while ((line = reader.readLine()) != null) {
            Matcher matcher = pattern.matcher(line);
            if (!matcher.find()) continue;
            String timeLocal = matcher.group("timeLocal");
            String requestMethod = matcher.group("requestMethod");

            TimeCount timeCount = null;

            //新的一秒
            if (!timeLocal.equals(time)) {
                if (tmpMap.size() > 0) {

                    tmpMap.clear();
                }

                time = timeLocal;
                timeCount = new TimeCount(timeLocal);
                tmpMap.put(requestMethod, timeCount);
            } else {//同一秒
                if (tmpMap.containsKey(requestMethod)) {
                    timeCount = tmpMap.get(requestMethod);
                    timeCount.setCount(timeCount.getCount() + 1);
                }
            }


        }


    }

}
