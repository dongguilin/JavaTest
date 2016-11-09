package com.guilin.java.guava.reflection;

import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by guilin on 2016/11/4.
 */
public class TestTypeToken {

    //泛型运行时类型信息会被擦除
    @Test
    public void test1() {
        ArrayList<String> stringList = Lists.newArrayList();
        ArrayList<Integer> intList = Lists.newArrayList();
        System.out.println("intList type is " + intList.getClass());
        System.out.println("stringList type is " + stringList.getClass());
        System.out.println(stringList.getClass().isAssignableFrom(intList.getClass()));
    }

    @Test
    public void testTypeToken() {
        TypeToken typeToken = new TypeToken<ArrayList<String>>() {
        };
        //获得包装的java.lang.reflect.Type.
        Assert.assertEquals("java.util.ArrayList<java.lang.String>", typeToken.getType().toString());
        //返回大家熟知的运行时类
        Assert.assertEquals("class java.util.ArrayList", typeToken.getRawType().toString());

        TypeToken<?> genericTypeToken = typeToken.resolveType(ArrayList.class.getTypeParameters()[0]);
        Assert.assertTrue(String.class == genericTypeToken.getType());
    }
}
