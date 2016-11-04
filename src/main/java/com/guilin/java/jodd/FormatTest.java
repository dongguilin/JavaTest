package com.guilin.java.jodd;

import jodd.format.RomanNumber;
import org.junit.Test;

/**
 * Created by guilin on 2014/4/23.
 */
public class FormatTest {

    @Test
    public void testScanf() {
        //TODO  怎么用？
//        Scanf.scanf();
    }

    @Test
    public void testRomanNumber() {
        System.out.println(RomanNumber.convertToArabic("MMMCMVIII"));
        System.out.println(RomanNumber.convertToRoman(12));
    }
}
