package com.guilin.java6.util;

import org.junit.Test;

import java.util.Random;

public class RandomTest {

    /**
     * 如果用相同的种子创建两个 Random 实例，则对每个实例进行相同的方法调用序列，它们将生成并返回相同的数字序列
     */
    @Test
    public void testUtilRandom() {
        Random random1 = new Random(15);
        Random random2 = new Random(15);
        for (int i = 0; i < 20; i++) {
            System.out.print(random1.nextInt(10) + " ");
        }
        System.out.println();
        for (int i = 0; i < 20; i++) {
            System.out.print(random2.nextInt(10) + " ");
        }

    }

    /**
     * Math.Random返回带正号的 double 值，该值大于等于 0.0 且小于 1.0
     * 第一次调用该方法时，它将创建一个新的伪随机数生成器，与以下表达式完全相同 new
     * java.util.Random之后，新的伪随机数生成器可用于此方法的所有调用， 但不能用于其他地方。 此方法是完全同步的
     */
    @Test
    public void testMathRandom() {
        for (int i = 0; i < 20; i++) {
            double d = Math.random() * 32;
            System.out.print(d + " ");
        }
        System.out.println();
        for (int i = 0; i < 20; i++) {
            double d = Math.random() * 32;
            System.out.print(d + " ");
        }

    }

}
