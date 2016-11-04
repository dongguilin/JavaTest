package com.guilin.java.guava.functional;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by guilin on 2016/9/30.
 */
public class TestFunction2 {

    @Test
    public void test1() {
        //定义转换函数
        Function<School, String> f = new Function<School, String>() {
            @Override
            public String apply(School input) {
                if (input == null) return "";
                return input.location;
            }
        };

        //转换集合
        Collection<String> list = Collections2.transform(schools, f);

        //验证
        assertThat(list, hasItems("xi'an", "chengdu"));
    }

    @Test
    public void test2() {
        //School->location
        Function<School, String> f = new Function<School, String>() {
            @Override
            public String apply(School s) {
                if (s == null) {
                    return "";
                }
                return s.location;
            }

        };

        //String->大写
        Function<String, String> f2 = new Function<String, String>() {
            @Override
            public String apply(String input) {
                if (input == null) return "";
                return input.toUpperCase();
            }
        };

        //组合函数
        Function<School, String> f3 = Functions.compose(f2, f);

        Collection<String> locationList = Collections2.transform(schools, f3);

        assertThat(locationList, hasItems("XI'AN", "CHENGDU"));

    }

    @Test
    public void testForMap() {
        //测试对map的预定义函数，即使用forMap构造的函数，在集合转换的时候会被自动映射
        //1、定义映射表
        Map<School, String> m = Maps.newHashMap();
        m.put(schools.get(0), "hangtian");

        //m.put(schools.get(1), "tielu");

        //2、定义函数
        Function<School, String> f = Functions.forMap(m, "UNKNOWN");

        //3、转换，此时会对schools中的每个元素调用映射进行转换，得到学校属性
        //schools的key在m中存在，则返回m.getValue()，否则返回默认值UNKNOWN
        Collection<String> types = Collections2.transform(schools, f);

        //4、验证
        assertThat(types, hasItems("hangtian", "UNKNOWN"));
    }


    static class School {
        public String name;
        public String location;

        public static School newSchool(String name, String loc) {
            School s = new School();
            s.name = name;
            s.location = loc;
            return s;
        }
    }

    List<School> schools;

    @Before
    public void init() {
        schools = Lists.newLinkedList();
        schools.addAll(Arrays.asList(School.newSchool("xigongda", "xi'an"),
                School.newSchool("xijiaoda", "chengdu")));
    }

    @After
    public void end() {
        if (schools != null) {
            schools.clear();
        }
    }


}
