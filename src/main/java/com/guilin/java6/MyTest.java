package com.guilin.java6;


import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: hadoop
 * Date: 15-3-3
 * Time: 下午7:28
 * To change this template use File | Settings | File Templates.
 */
public class MyTest {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, IOException {


        Random random = new Random();
        List<Integer> busyList = new ArrayList();

        int _1Size = 0;
        for (int i = 0; i < 50; i++) {
            int value = random.nextInt(100);
            if (value > 70 && value < 90) {
                value = -1;
                _1Size++;
            }
            busyList.add(value);
        }

        List<Integer> idleList = new ArrayList<Integer>();
        for (int i = 0; i < _1Size; i++) {
            idleList.add(random.nextInt(70));
        }

        System.out.println(busyList);
        System.out.println(idleList);


        List<Object> busyData = new ArrayList();//正常点
        List<Object> idleData = new ArrayList();//闲时点
        List<Object> idleChartData = new ArrayList();//闲时点（用于echarts绘图）

        int idleIndex = 0;
        boolean flag = false;
        for (int i = 0; i < busyList.size(); i++) {
            int busy = busyList.get(i);
            if (busy == -1) {//闲时点
                busyData.add(-1);
                idleData.add(idleList.get(idleIndex));
                idleChartData.add(idleList.get(idleIndex));
                if (flag) {
                    int a = idleChartData.size() - 2 < 0 ? 0 : idleChartData.size() - 2;
                    idleChartData.set(a, 0);
                    flag = false;
                }
                idleIndex++;

            } else {//正常点
                busyData.add(busy);
                idleData.add("-");
                idleChartData.add("-");
                flag = true;
            }
        }

        System.out.println("");
        System.out.println(busyData);
        System.out.println(idleData);
        System.out.println(idleChartData);

        System.out.println(busyData.size() == idleData.size() && busyData.size() == idleChartData.size());


    }

    @Test
    public void test2() {
        Map<String, Set<String>> appids = new HashMap<String, Set<String>>();
        File file = new File("/Users/guilin1/Downloads/user_behavior.txt");
        BufferedReader fr = null;
        try {
            fr = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = null;

            String[] fields = {"appId", "appVersion", "deviceId", "", "field4", "field5", "field6", "field7", "field8", "field9"};
            while ((line = fr.readLine()) != null) {
                String[] arr = line.split("\\|");
                String appid = arr[0];
                String appversion = arr[1];
                if (appids.get(appid) == null) {
                    appids.put(appid, new HashSet<String>());
                }
                appids.get(appid).add(appversion);
            }
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() throws Exception {

        String str = "0,162025.29000000000814907252788543701171875,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,18117.9300000000002910383045673370361328125,0,0,";
        String[] arr = str.split(",");
        System.out.println(arr.length);
        for (String s : arr) {
            System.out.println(s);
        }
    }


    @Test
    public void test4() {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.printf(j + "*" + i + "=" + "%2d ", (i * j));
            }
            System.out.println();
        }

    }

    @Test
    public void test5() {
        for (int i = 1, j = i; i <= 9; ) {
            System.out.printf(j + "*" + i + "=" + "%2d ", (i * j));
            j++;
            if (j > i) {
                i++;
                j = 1;
                System.out.println();
            }

        }
    }

    @Test
    public void test6() {
        int j = 1;
        for (int i = 1; i <= 9; ) {
            System.out.printf(j + "*" + i + "=" + "%2d ", (i * j));
            j++;
            if (j > i) {
                i++;
                j = 1;
                System.out.println();
            }

        }
    }

    @Test
    public void test7() {
        String mac = "00-E0-20-1C-7C-0C";
        String mac2 = "000c-29c4-1daf";
        //正则校验MAC合法性
        String patternMac = "^[a-f 0-9]{4}(-[a-f 0-9]{4}){2}$";
        if (!Pattern.compile(patternMac).matcher(mac2).find()) {
            System.out.println(false);
        } else {
            System.out.println(true);
        }
    }

    @Test
    public void test8() {
        Map<String, String> map = new HashMap<>();
        MapUtils.putAll(map, new Object[]{"hello", "world", "aa", "bb"});
        MapUtils.putAll(map, new Object[]{"11:111", "22:222"});
        System.out.println(map);
        System.out.println(map.get("aa"));

        long l = new Date().getTime();
        System.out.println(l);
        System.out.println(l / 60000 * 60000);

        System.out.println(StringUtils.isEmpty(""));
        System.out.println(StringUtils.isEmpty(null));
        System.out.println(StringUtils.isEmpty(" "));

    }

    @Test
    public void test9() {
        final int[] arr = new int[10];
        arr[3] = 3;
        System.out.println(Arrays.toString(arr));

    }


    @Test
    public void test11() throws ParseException {
        String str = "aa'b\"a'a\"aa\"a'a\"b'aa";
        System.out.println(str);

        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";
        String s4 = "ab";
        System.out.println(s3 == s4);//true
        String s5 = "a" + "b";
        System.out.println(s3 == s5);//true
        String s6 = s1 + s2;
        System.out.println(s3 == s6);//false
        String s7 = new String("ab");
        System.out.println(s3 == s7);//false
        final String s8 = "a";
        final String s9 = "b";
        String s10 = s8 + s9;
        System.out.println(s3 == s10);//true


    }




}



