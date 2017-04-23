package com.guilin.java.commons.lang3;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Administrator on 2017/4/23.
 */
public class TestStringUtils {

    @Test
    public void test1() {
        String str = null;
        System.out.println(StringUtils.isEmpty(str) + "," + StringUtils.isBlank(str));//true,true
        str = "";
        System.out.println(StringUtils.isEmpty(str) + "," + StringUtils.isBlank(str));//true,true
        str = " ";
        System.out.println(StringUtils.isEmpty(str) + "," + StringUtils.isBlank(str));//false,true
        str = "null";
        System.out.println(StringUtils.isEmpty(str) + "," + StringUtils.isBlank(str));//false,false
        str = "Null";
        System.out.println(StringUtils.isEmpty(str) + "," + StringUtils.isBlank(str));//false,false

    }

    @Test
    public void test2() {
        // data setup
        String str1 = "";
        String str2 = "";
        String str3 = "\t";
        String str4 = null;
        String str5 = "123";
        String str6 = "ABCDEFG";
        String str7 = "Itfeels good to use JakartaCommons.\r\n";

        // check for empty strings
        assertThat(StringUtils.isBlank(str1), is(true));
        assertThat(StringUtils.isBlank(str2), is(true));
        assertThat(StringUtils.isBlank(str3), is(true));
        assertThat(StringUtils.isBlank(str4), is(true));

        // check for numerics
        assertThat(StringUtils.isNumeric(str5), is(true));
        assertThat(StringUtils.isNumeric(str6), is(false));

        // reverse strings / whole words
        assertThat(StringUtils.reverse(str6), is("GFEDCBA"));
        assertThat(str6, is("ABCDEFG"));
        String str8 = StringUtils.chomp(str7);
        assertThat(str8, is("Itfeels good to use JakartaCommons."));
        assertThat(StringUtils.reverseDelimited(str8, ' '), is("JakartaCommons. use to good Itfeels"));

        // build header (useful to print logmessages that are easy to locate)
        System.out.println("==============================");
        System.out.println("print header:");
        String padding = StringUtils.repeat("=", 50);
        String msg = StringUtils.center(" Customised Header ", 50, "%");
        Object[] raw = new Object[]{padding, msg, padding};
        String header = StringUtils.join(raw, "\r\n");
        System.out.println(header);
    }
}
