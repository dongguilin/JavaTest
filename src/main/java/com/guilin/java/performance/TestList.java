package com.guilin.java.performance;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

/**
 * Created by guilin on 2017/3/28.
 * ArrayList遍历速度： for循环>迭代器>forEach操作
 * LinkedList遍历速度：迭代器>forEach操作>for循环
 */
public class TestList {

    @Test
    public void test1() {
        List<String> list = Lists.asList("a", "b", new String[]{"c", "d", "e"});
        String tmp;
        for (String s : list) {
            tmp = s;
        }
    }

    //比forEach更快
    @Test
    public void test2() {
        List<String> list = Lists.asList("a", "b", new String[]{"c", "d", "e"});
        String tmp;
        for (Iterator<String> it = list.iterator(); it.hasNext(); ) {
            tmp = it.next();
        }
    }
}
