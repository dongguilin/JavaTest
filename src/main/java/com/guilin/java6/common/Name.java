package com.guilin.java6.common;

public class Name {
    /**
     * （变量名/方法名/类名）可以是英文字母、阿拉伯数字、下划线、$符号的组合，但不能以数字开头，区分大小写, 可以是中文，不能是java关键字
     */
    int $hehe;
    int _123;
    int a_a$1;
    int a$_;
    int New;
    String s;

    void $1a() {
    }

    int 呵_$呵;

    @SuppressWarnings("unused")
    private class New {

    }

    private interface New2 {
        int a = 1;
        static String s = null;
        static final String ss = "";
        public static final int b = 11;

        void a();

    }

    interface New3 extends New2 {
        int a = 2;
        int b = 12;
    }

    abstract class New5 {
        void a() {
            System.out.println("hehe");
        }
    }

    class New4 extends New5 implements New3 {
        int a = 11;

        @Override
        public void a() {
            System.out.println("a");
        }

    }

}
