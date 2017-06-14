package com.guilin.java.guava.basic;

import com.google.common.base.Throwables;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by guilin on 2016/11/9.
 */
public class TestThrowables {

    @Test
    public void test1() {
        try {
            t1();
            System.out.println("hehe");
        } catch (Throwable e) {
            System.out.println(e.getClass());
            System.out.println(Throwables.getStackTraceAsString(e));
            System.out.println("////////");
            System.out.println(Throwables.getRootCause(e));
        }
        System.out.println("hello");


    }

    private void t1() {
        try {
//            throw new IOError(null);
//            int a = 1 / 0;
//            new ArrayList<>().get(2);
            new FileInputStream(new File("a.txt"));
//        } catch (IOException e1) {
//            System.out.println("IOException");
//            e1.printStackTrace();
        } catch (NullPointerException e2) {
            System.out.println("NullPointerException");
            e2.printStackTrace();
        } catch (Throwable e) {
            System.out.println(e.getClass());
            Throwables.propagate(e);
        }
    }


}
