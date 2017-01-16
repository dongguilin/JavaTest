package com.guilin.java.nginxlog;

import com.google.common.collect.ConcurrentHashMultiset;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by guilin on 2017/1/16.
 */
public class TestNginx {

//    @Test
//    public void test1(){
//        String str = "223.104.18.155 - - [28/Nov/2016:23:55:10 +0800] \"GET /elewebapp/js/jquery.validate.ms.js?v=1.0 HTTP/1.1\" 200 10146 \"https://elewebapp.95598pay.com/elewebapp/login/toLogin.action\" \"Mozilla/5.0 (Linux; Android 6.0.1; LG-K220 Build/MXB48T; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/50.0.2661.86 Mobile Safari/537.36\"";
//        String regex = "^[^\\[\\n]*\\[(?P<time>[^ ]+)[^\"\\n]*\"\\w+\\s+(?P<url>[^ ]+)";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(str);
//        if(matcher.find()){
//            System.out.println(matcher.group("time"));
//            System.out.println(matcher.group("url"));
//        }
//
//    }

    @Test
    public void test2() {
        Multiset<String> multiset = HashMultiset.create();
        multiset.add("a");
        multiset.add("a");
        multiset.add("a");
        multiset.add("b");
        multiset.add("b");
        System.out.println(multiset.count("a"));
        System.out.println(multiset.count("b"));
        System.out.println(multiset.entrySet());
        System.out.println(multiset.size());
        System.out.println(multiset.toArray());
    }

    @Test
    public void test3() {
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("time1", 12);
        treeMap.put("time2", 10);
        treeMap.put("time3", 10);
        treeMap.put("time4", 9);
        treeMap.put("time5", 9);
        treeMap.put("time6", 9);
        treeMap.put("time7", 9);
        treeMap.put("time8", 8);
        treeMap.put("time9", 5);
        System.out.println(treeMap);
    }

    private static Pattern pattern = null;

    public TestNginx() {
        String regex = "(?<remoteAddr>\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}) - ((\\s+)|-) \\[(?<timeLocal>.*)\\] \"(?<requestMethod>POST|GET) (?<requestUrl>[^ ]+) ([^\"]*)\" (?<status>\\d+)";
        pattern = Pattern.compile(regex);
    }

    private void splitFile(File file) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = null;
            while ((line = reader.readLine()) != null) {
                Request request = parse(line);
                if (request != null) {
                    String url = request.getUrl();

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Request parse(String line) {
        //解析出请求url和时间
        Matcher matcher = pattern.matcher(line);
        if (!matcher.find()) return null;

        String timeLocal = matcher.group("timeLocal");
        String requestMethod = matcher.group("requestMethod");

        Request request = new Request();
        request.setTime(convertTime(timeLocal));
        request.setUrl(requestMethod);
        return request;
    }

    /**
     * 28/Nov/2016:23:51:09 +0800转换为20161128235109
     *
     * @param time
     * @return
     */
    private String convertTime(String time) {
        return null;
    }


    public static void main(String[] args) {

        String regex = "(?<remoteAddr>\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}) - ((\\s+)|-) \\[(?<timeLocal>.*)\\] \"(?<requestMethod>POST|GET) (?<requestUrl>[^ ]+) ([^\"]*)\" (?<status>\\d+)";
        Pattern pattern = Pattern.compile(regex);

        Map<String, Request> map = new HashedMap();

        try {
            File file = new File("E:/53elewebapp.log");
            List<String> lines = FileUtils.readLines(file, "UTF-8");

            for (String line : lines) {
                Matcher matcher = pattern.matcher(line);
                if (!matcher.find()) continue;
                String timeLocal = matcher.group("timeLocal");
                String requestMethod = matcher.group("requestMethod");

                Request request = null;
                if (!map.containsKey(requestMethod)) {
                    request = new Request();
                    map.put(requestMethod, request);
                }


            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
