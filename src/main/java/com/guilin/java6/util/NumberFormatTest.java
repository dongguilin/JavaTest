package com.guilin.java6.util;

import org.junit.Test;

import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatTest {

    @Test
    public void test1() {
        int price = 12;
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        System.out.println(nf.format(price));

        Locale locale = Locale.FRENCH;
        nf = NumberFormat.getCurrencyInstance(locale);
        System.out.println(nf.format(price));
    }

    @Test
    public void test2() throws Exception {
        NumberFormat nf = NumberFormat.getInstance();
        System.out.println(nf.parse("12,34,212.33f").toString());
        URL url = NumberFormatTest.class.getClassLoader().getResource("");
        System.out.println(url.toString());
        System.out.println(url.getPath());

    }
}