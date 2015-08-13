package com.guilin.java6.commonsnet;

import org.apache.commons.collections.map.HashedMap;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by guilin1 on 15/7/29.
 */
public class Demo1 {


    public static void main(String[] args) throws Exception {

        Map<String, Map<String, String>> map = new HashedMap();

        File file = new File("/Users/guilin1/Documents/test/arp.info");
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String str;
        String[] arr;
        while ((str = reader.readLine()) != null) {
//            System.out.println(str);
            arr = str.replace("---- More ----", "").split(" +");
            if (arr.length == 5 && isIp(arr[0])) {
                String ip = arr[0];
                if (map.containsKey(ip)) {
                    continue;
                }
                Map<String, String> temp = new HashMap();
                temp.put("ip", ip);
                temp.put("mac", arr[1]);
                temp.put("interface", arr[4]);
                map.put(ip, temp);
            }
        }
        reader.close();

        print(map);


    }

    private static void print(Map<String, Map<String, String>> map) {
        if (map == null || map.size() == 0) {
            return;
        }
        System.out.println(map.size());
        Iterator<Map<String, String>> iterator = map.values().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public static boolean isIp(String text) {
        if (text != null && !text.isEmpty()) {
            String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
            if (text.matches(regex)) {
                return true;
            }
        }
        return false;
    }

}
