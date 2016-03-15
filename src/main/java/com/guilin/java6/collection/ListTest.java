package com.guilin.java6.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by guilin1 on 16/3/14.
 */
public class ListTest {

    @Test
    public void test1() {
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        list.add("zhangsan");
        list.add("lisi");
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        System.out.println(list);
        ;
    }
}
