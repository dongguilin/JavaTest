package com.guilin.java.arithmetic.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by guilin on 2017/2/22.
 * 冒泡排序
 */
public class BubbleSort {

    @Test
    public void test1() {
        int[] array = new int[]{3, 10, 5, 9, 2, 6, 12, 11, 14, 5, 7, 13, 22, 1, 8};
        System.out.println("数组长度：" + array.length);
        System.out.println("初始数组：" + Arrays.toString(array));
        int times = 0;//排序比较次数
        int move = 0;//移动次数

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                times++;
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    move++;
                    System.out.println(Arrays.toString(array));
                }
            }
        }

        System.out.println("比较次数：" + times);
        System.out.println("移动次数：" + move);
        System.out.println("排序后：" + Arrays.toString(array));
    }

}
