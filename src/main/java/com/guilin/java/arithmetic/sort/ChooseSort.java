package com.guilin.java.arithmetic.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by guilin on 2017/2/22.
 * 选择排序
 * 比较次数n*(n-1)/2，移动次数n-1
 * 相比较冒泡排序，排序次数一样，移动次数减少
 * 时间复杂度n2
 */
public class ChooseSort {

    @Test
    public void test1() {
        int[] array = new int[]{3, 10, 5, 9, 2, 6, 12, 11, 14, 5, 7, 13, 22, 1, 8};
        System.out.println("数组长度：" + array.length);
        System.out.println("初始数组：" + Arrays.toString(array));
        int times = 0;//排序比较次数
        int move = 0;//移动次数
        for (int i = 0; i < array.length - 1; i++) {
            int least = i;
            for (int j = i + 1; j < array.length; j++) {
                times++;
                System.out.print("(" + i + "," + j + ") ");
                if (array[j] < array[least]) {
                    least = j;
                }
            }
            int tmp = array[i];
            array[i] = array[least];
            array[least] = tmp;
            move++;
            System.out.println();
        }
        System.out.println("比较次数：" + times);
        System.out.println("移动次数：" + move);
        System.out.println("排序后：" + Arrays.toString(array));
    }

}
