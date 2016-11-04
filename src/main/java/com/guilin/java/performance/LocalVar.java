package com.guilin.java.performance;

import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;

import java.util.Map;

/**
 * Created by guilin on 2016/9/8.
 * 调用方法时传递的参数以及在调用中创建的临时变量都保存在栈(Stack)中，速度较快。
 * 其他变量，如静态变量、实例变量等，都在堆(Heap)中创建，速度较慢。
 */
public class LocalVar {

    private Map<String, Object> configs = ConfigData.getData();

    private int ta = 0;

    private int num = new ConfigData().getNum();


    //test1与test2和test3速度差不多
    @Test
    public void test1() {
        for (int i = 0; i < 100000000; i++) {
            configs.containsKey("hello");
//            num++;
        }
    }

    @Test
    public void test2() {
        Map<String, Object> maps = ConfigData.getData();
//        int numl = 0;
        for (int i = 0; i < 100000000; i++) {
            maps.containsKey("hello");
//            numl++;
        }
    }

    @Test
    public void test3() {
        Map<String, Object> maps = new HashedMap();
        maps.put("hello", "world");
        for (int i = 0; i < 100000000; i++) {
            maps.containsKey("hello");
        }
    }

    //运行1亿次，花费8ms
    @Test
    public void test4() {
        int a = 0;
        for (int i = 0; i < 100000000; i++) {
            a++;
        }
    }

    //运行1亿次，花费15ms
    @Test
    public void test5() {
        for (int i = 0; i < 100000000; i++) {
            ta++;
        }
    }


}
