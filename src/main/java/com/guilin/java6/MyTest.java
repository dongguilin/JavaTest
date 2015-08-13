package com.guilin.java6;


import com.aleiye.predict.FlowPredictDriver;
import com.guilin.java6.mongodb.Student;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
    public void test3() throws Exception {
        FlowPredictDriver driver = new FlowPredictDriver();
        for (int i = 0; i < 20; i++) {
            List<String> list = Arrays.asList("1.756947E8", "1.71434671E8", "1.77902792E8", "1.73640681E8", "1.77915004E8", "1.77109538E8", "1.7815381E8");
            List<String> list2 = Arrays.asList("1.77769895E8", "1.79594015E8", "1.72058103E8", "1.73167125E8", "1.74613375E8", "1.7339751E8", "1.78464254E8");
            List<String> list3 = Arrays.asList("1.77524492E8", "1.71380104E8", "1.81280052E8", "1.76192032E8", "1.76021605E8", "1.7467288E8", "1.74389429E8");
            List<String> list4 = Arrays.asList("1.73220554E8", "1.79038993E8", "1.74643583E8", "1.76051063E8", "1.72299728E8", "1.79278453E8", "1.79530007E8");
            List<String> list5 = Arrays.asList("1.81918562E8", "1.79036329E8", "1.75632391E8", "1.74962019E8", "1.7341566E8", "1.8316149E8", "1.75030843E8");
            List<String> list6 = Arrays.asList("1.78640892E8", "170714.0");


            List<List<String>> tmp = new ArrayList<List<String>>();
            tmp.add(list);
            tmp.add(list2);
            tmp.add(list3);
            tmp.add(list4);
            tmp.add(list5);
            tmp.add(list6);


////		List<List<Double>> data2 = new ArrayList<List<Double>>();
////
////		FlowPredictDriver driver = new FlowPredictDriver();
////		driver.getColumnCollection(tmp, 0, data2);
////		System.out.println(data2.size());
//

            List<List<Double>> result = driver.run(tmp, 1, 7, true, 16);
            System.out.println(result);
//
//		FlowPredictDriver driver = new FlowPredictDriver();
//		IDataSouce ds = new MySqlDataSource("aleiyeB", 3306, "a_dm_1", "aleiye", "cdewsxzaq321", "utf8");
//		List<List<Double>> run = driver.run(ds, 1, 1440, true, "2014-08-23", "2014-08-24", "tbl_d_traffic", "date", "data", -1);
//		System.out.println(run);

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
    public void test7(){
        String mac="00-E0-20-1C-7C-0C";
        String mac2="000c-29c4-1daf";
        //正则校验MAC合法性
        String patternMac="^[a-f 0-9]{4}(-[a-f 0-9]{4}){2}$";
        if(!Pattern.compile(patternMac).matcher(mac2).find()){
            System.out.println(false);
        }else {
            System.out.println(true);
        }
    }

    @Test
    public void test8(){
        Map<String,String> map = new HashMap<>();
        MapUtils.putAll(map, new Object[]{"hello", "world", "aa", "bb"});
        MapUtils.putAll(map, new Object[]{"11:111", "22:222"});
        System.out.println(map);
        System.out.println(map.get("aa"));

        long l=new Date().getTime();
        System.out.println(l);
        System.out.println(l/60000*60000);

        System.out.println(StringUtils.isEmpty(""));
        System.out.println(StringUtils.isEmpty(null));
        System.out.println(StringUtils.isEmpty(" "));

    }



}



