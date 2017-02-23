package com.guilin.java.arithmetic.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by guilin on 2017/2/22.
 * 冒泡排序
 * 比较次数n*(n-1)/2，最好情况移动次数0，最坏情况移动次数n*(n-1)/2，时间复杂度n2
 */
public class BubbleSort {

    @Test
    public void test1() {
        int[] array = new int[]{3, 10, 5, 9, 2, 6, 12, 11, 14, 5, 7, 13, 22, 1, 8};
        System.out.println("数组长度：" + array.length);
        System.out.println("初始数组：" + Arrays.toString(array));
        int times = 0;//排序比较次数
        int move = 0;//移动次数
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                int a = array[i];
                int b = array[j];
                System.out.print("(" + i + "," + j + ") ");
                if (a < b) {
                    array[i] = b;
                    array[j] = a;
                    move++;
                }
                times++;
            }
            System.out.println();
        }
        System.out.println("比较次数：" + times);
        System.out.println("移动次数：" + move);
        System.out.println("排序后：" + Arrays.toString(array));
    }

    @Test
    public void test2() {
        int[] array = new int[]{3, 10, 5, 9, 2, 6, 12, 11, 14, 5, 7, 13, 22, 1, 8};
        System.out.println("数组长度：" + array.length);
        System.out.println("初始数组：" + Arrays.toString(array));
        int times = 0;//排序比较次数
        int move = 0;//移动次数
        for (int i = array.length - 1, j = i - 1; i > 0 && j >= 0; ) {
            int a = array[i];
            int b = array[j];
            System.out.print("(" + i + "," + j + ") ");
            times++;
            if (a < b) {
                array[i] = b;
                array[j] = a;
                move++;
            }
            if (j == 0) {
                --i;
                j = i - 1;
                System.out.println();
            } else {
                j--;
            }
        }
        System.out.println("比较次数：" + times);
        System.out.println("移动次数：" + move);
        System.out.println("排序后：" + Arrays.toString(array));
    }
}
