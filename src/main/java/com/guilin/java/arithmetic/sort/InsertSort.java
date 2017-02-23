package com.guilin.java.arithmetic.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by guilin on 2017/2/23.
 * 插入排序
 */
public class InsertSort {

    /**
     * 直接插入排序
     * 最好情况比较次数n-1，移动次数0
     * 最坏情况比较次数n*(n-1)/2，移动次数n*(n-1)/2
     * 平均比较次数和移动次数n2/4
     */
    @Test
    public void test1() {
        int[] array = new int[]{3, 10, 5, 9, 2, 6, 12, 11, 14, 5, 7, 13, 22, 1, 8};
        System.out.println("数组长度：" + array.length);
        System.out.println("初始数组：" + Arrays.toString(array));

        for (int i = 1; i < array.length; i++) {

            int j = i;
            while (j > 0 && array[j] < array[j - 1]) {
                //交换array[j]与array[j-1]
                int tmp = array[j];
                array[j] = array[j - 1];
                array[j - 1] = tmp;

                j--;
                System.out.println(Arrays.toString(array));
            }
            System.out.println();
        }

        System.out.println("排序后：" + Arrays.toString(array));
    }
}
