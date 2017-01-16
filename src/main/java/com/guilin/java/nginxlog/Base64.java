package com.guilin.java.nginxlog;

import org.junit.Test;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.TreeMap;

/**
 * Created by guilin on 2017/1/16.
 */
public class Base64 {

    @Test
    public void test1(){
        String str = getBase64("/elewebapp/js/jquery.metadata.js");
        System.out.println(str);
        System.out.println(getFromBase64(str));

        File file = new File("e:/53elewebapp.log");
        System.out.println(file.length()/1024.0);



    }

    // 加密
    public static String getBase64(String str) {
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {
            s = new BASE64Encoder().encode(b);
        }
        return s;
    }

    // 解密
    public static String getFromBase64(String s) {
        byte[] b = null;
        String result = null;
        if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(s);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
