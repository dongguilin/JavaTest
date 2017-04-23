package com.guilin.java.commons.lang3;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.util.Map;

/**
 * Created by Administrator on 2017/4/23.
 */
public class TestArrayUtils {

    /**
     * 利用ArrayUtils类帮我们完成数组的打印、查找、克隆、倒序、以及值型/对象数组之间的转换等操作
     */
    @Test
    public void test1() {
        // data setup
        int[] intArray1 = {2, 4, 8, 16};
        int[][] intArray2 = {{1, 2}, {2, 4}, {3, 8}, {4, 16}};
        Object[][] notAMap = {
                {"A", new Double(100)},
                {"B", new Double(80)},
                {"C", new Double(60)},
                {"D", new Double(40)},
                {"E", new Double(20)}
        };

        // printing arrays
        System.out.println("intArray1: " + ArrayUtils.toString(intArray1));
        System.out.println("intArray2: " + ArrayUtils.toString(intArray2));
        System.out.println("notAMap: " + ArrayUtils.toString(notAMap));

        // finding items
        System.out.println("intArray1 contains '8'? "
                + ArrayUtils.contains(intArray1, 8));
        System.out.println("intArray1 index of '8'? "
                + ArrayUtils.indexOf(intArray1, 8));
        System.out.println("intArray1 last index of '8'? "
                + ArrayUtils.lastIndexOf(intArray1, 8));

        // cloning and resversing
        int[] intArray3 = ArrayUtils.clone(intArray1);
        System.out.println("intArray3: " + ArrayUtils.toString(intArray3));
        ArrayUtils.reverse(intArray3);
        System.out.println("intArray3 reversed: "
                + ArrayUtils.toString(intArray3));

        // primitive to Object array
        Integer[] integerArray1 = ArrayUtils.toObject(intArray1);
        System.out.println("integerArray1: "
                + ArrayUtils.toString(integerArray1));

        // build Map from two dimensional array
        Map map = ArrayUtils.toMap(notAMap);
        Double res = (Double) map.get("C");
        System.out.println("get 'C' from map: " + res);
    }
}
