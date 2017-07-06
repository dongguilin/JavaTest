package com.guilin.java.oop.extendssuper;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guilin on 2017/7/6.
 * <? super E> 是 Lower Bound（下限） 的通配符 ，用来限制元素的类型下限
 * <? extends E> 是 Upper Bound（上限） 的通配符，用来限制元素的类型的上限
 */
public class Client {

    @Test
    public void test1() {
        List<? super Fruit> appList = new ArrayList<>();
        appList.add(new Fruit());
        appList.add(new Apple());
        appList.add(new RedApple());
    }

    @Test
    public void test2() {
        List<? super Apple> appList = new ArrayList<Apple>();//表示集合中元素类型下限为Apple类型，即只能是Apple或Apple的父类
        appList = new ArrayList<Fruit>();
        appList = new ArrayList<Plant>();
        //appList = new ArrayList<RedApple>();//编译不通过
    }

    @Test
    public void test3() {
        List<? extends Fruit> fruits = new ArrayList<Fruit>();//表示集合中的元素类型上限为Fruit类型，即只能是Fruit或者Fruit的子类
        fruits = new ArrayList<Apple>();
        fruits = new ArrayList<RedApple>();
        //fruits = new ArrayList<Object>();//编译不通过

        //不能写入，只能读取
        Fruit fruit = fruits.get(0);

    }

    @Test
    public void test4() {

    }
}
