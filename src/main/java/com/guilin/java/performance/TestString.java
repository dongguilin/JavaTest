package com.guilin.java.performance;

import org.junit.Test;

import java.text.ParseException;

/**
 * Created by guilin on 2017/3/28.
 */
public class TestString {

    @Test
    public void test1() {
        for (int i = 0; i < 1000000; i++) {
            String result = "String" + "and" + "String" + "append";//编译时会做优化，编译为String str = "StringandStringappend";
        }
    }

    @Test
    public void test2() {
        for (int i = 0; i < 1000000; i++) {
            StringBuilder result = new StringBuilder();
            result.append("String");
            result.append("and");
            result.append("String");
            result.append("append");
        }
    }

    @Test
    public void test3() {
        for (int i = 0; i < 1000000; i++) {
            String str1 = "String";
            String str2 = "and";
            String str3 = "String";
            String str4 = "append";
            String result = str1 + str2 + str3 + str4;//编译时并没有被优化
        }
    }

    @Test
    public void test4() {
        for (int i = 0; i < 1000000; i++) {
            final String str1 = "String";
            final String str2 = "and";
            final String str3 = "String";
            final String str4 = "append";
            String result = str1 + str2 + str3 + str4;//编译时会被优化,String str = "StringandStringappend";
        }
    }

    @Test
    public void test5() throws ParseException {
        String str = "aa'b\"a'a\"aa\"a'a\"b'aa";
        System.out.println(str);

        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";
        String s4 = "ab";
        System.out.println(s3 == s4);//true
        String s5 = "a" + "b";//编译时会被优化，编译为String s5 = "ab";
        System.out.println(s3 == s5);//true
        String s6 = s1 + s2;
        System.out.println(s3 == s6);//false
        String s7 = new String("ab");
        System.out.println(s3 == s7);//false
        final String s8 = "a";
        final String s9 = "b";
        String s10 = s8 + s9;//编译时会被优化，编译为String s10 = "ab";
        System.out.println(s3 == s10);//true
    }

    //速度：StringBuilder>concat>+
    @Test
    public void test6() {
        String str = "";
        for (int i = 0; i < 10000; i++) {
            str = str + i;
        }
    }

    @Test
    public void test7() {
        String str = "";
        for (int i = 0; i < 10000; i++) {
            str = str.concat(String.valueOf(i));
        }
    }

    @Test
    public void test8() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append(i);
        }
    }


}
