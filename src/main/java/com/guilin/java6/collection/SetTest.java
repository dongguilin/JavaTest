package com.guilin.java6.collection;

import org.junit.Test;

import java.util.*;

/**
 * 不包含重复元素的 collection,最多包含一个 null 元素
 * HashSet，TreeSet(看比较器是否允许null元素)，LinkedHashSet，EnumSet（不允许null元素）
 *
 * @author Administrator
 */
public class SetTest {

    /**
     * 顺序不确定
     */
    @Test
    public void testHashSet() {
        Set<Integer> set = new HashSet<Integer>(10);
        set.add(15);
        set.add(22);
        set.add(12);
        set.add(1);
        set.add(10);
        set.add(2);
        set.add(null);
        set.add(11);
        set.add(1);
        set.add(null);
        System.out.println("HashSet:");
        printSet(set);
    }

    /**
     * 默认由小到大
     */
    @Test
    public void testTreeSet() {
        Set<Integer> set = new TreeSet<Integer>();
        set.add(15);
        set.add(22);
        set.add(12);
        set.add(1);
        set.add(10);
        set.add(2);
        // 如果指定元素为 null，并且此 set 使用自然顺序，或者其比较器不允许使用 null
        // 元素,则抛出NullPointerException
        // set.add(null);
        set.add(11);
        set.add(1);
        System.out.println("TreeSet");
        printSet(set);
    }

    /**
     * 与添加时顺序一致
     */
    @Test
    public void testLinkedHashSet() {
        Set<Integer> set = new LinkedHashSet<Integer>();
        set.add(15);
        set.add(22);
        set.add(12);
        set.add(1);
        set.add(10);
        set.add(2);
        set.add(null);
        set.add(11);
        set.add(1);
        set.add(null);
        System.out.println("LinkedHashSet");
        printSet(set);
    }

    @Test
    public void testEnumSet() {
        Set<Student> set = EnumSet.of(Student.LiSi, Student.ZhangSan,
                Student.LiSi);
        printSet(set);
    }

    public enum Student {
        ZhangSan() {
            @Override
            public String toString() {
                return "ZhangSan 12";
            }
        },
        LiSi() {
            @Override
            public String toString() {
                return "LiSi 22";
            }
        };
    }

    private static void printSet(Set set) {
        for (Object obj : set) {
            System.out.print(obj + " ");
        }
        System.out.println();
    }

}