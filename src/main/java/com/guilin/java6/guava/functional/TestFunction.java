package com.guilin.java6.guava.functional;

import com.google.common.base.CharMatcher;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multiset;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by guilin on 2016/9/30.
 * 借助guava，JDK5以上即可使用函数式编程
 * 过度使用Guava函数式编程会导致冗长、混乱、可读性差而且低效的代码
 * <p>
 * 截至JDK7，命令式代码仍应是默认和第一选择。不应该随便使用函数式风格，除非你绝对确定以下两点之一：
 * 1.使用函数式风格以后，整个工程的代码行会净减少
 * 2.为了提高效率，转换集合的结果需要懒视图，而不是明确计算过的集合,此外，确保你已经阅读和重读了Effective Java的第55条，并且除了阅读本章后面的说明，你还真正做了性能测试并且有测试数据来证明函数式版本更快。
 * <p>
 * Guava提供两个基本的函数式接口：
 * Function<A, B>
 * Predicate<T>
 */
public class TestFunction {

    //实现不简洁，可读性差并且效率较低
    @Test
    public void test1() {
        Function<String, Integer> lengthFunction = new Function<String, Integer>() {
            public Integer apply(String string) {
                return string.length();
            }
        };
        Predicate<String> allCaps = new Predicate<String>() {
            public boolean apply(String string) {
                return CharMatcher.JAVA_UPPER_CASE.matchesAllOf(string);
            }
        };

        List<String> strings = Arrays.asList("hello world", "ZHANGSAN");

        Multiset<Integer> lengths = HashMultiset.create(
                Iterables.transform(Iterables.filter(strings, allCaps), lengthFunction));
        System.out.println(lengths);
    }

    //FluentIterable的版本
    @Test
    public void test2() {
        List<String> strings = Arrays.asList("hello world", "ZHANGSAN");

        Multiset<Integer> lengths = HashMultiset.create(
                FluentIterable.from(strings)
                        .filter(new Predicate<String>() {
                            public boolean apply(String string) {
                                return CharMatcher.JAVA_UPPER_CASE.matchesAllOf(string);
                            }
                        })
                        .transform(new Function<String, Integer>() {
                            public Integer apply(String string) {
                                return string.length();
                            }
                        }));
        System.out.println(lengths);
    }

    @Test
    public void test3() {
        List<String> strings = Arrays.asList("hello world", "ZHANGSAN");
        Multiset<Integer> lengths = HashMultiset.create();
        for (String string : strings) {
            if (CharMatcher.JAVA_UPPER_CASE.matchesAllOf(string)) {
                lengths.add(string.length());
            }
        }
        System.out.println(lengths);
    }


}
