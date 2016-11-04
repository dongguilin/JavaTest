package com.guilin.java6.lang;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guilin on 2016/9/19.
 */
public class StringTest {

    static class HugeStr {
        private String str = new String(new char[100000]);

        public String getSubString(int begin, int end) {
            return str.substring(begin, end);
        }
    }

    static class ImprovedHugeStr {
        private String str = new String(new char[100000]);

        public String getSubString(int begin, int end) {
            return new String(str.substring(begin, end));
        }
    }

    public static void main(String[] args) {
        List<String> handler = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
//            HugeStr h = new HugeStr();
            ImprovedHugeStr h = new ImprovedHugeStr();
            handler.add(h.getSubString(1, 155));
        }
    }

}
