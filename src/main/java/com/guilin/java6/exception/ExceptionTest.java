package com.guilin.java6.exception;

/**
 * Created by guilin1 on 15/4/2.
 */
public class ExceptionTest {

    static void methodA() throws ExceptionA {
        System.out.println("methodA invoke!");
        throw new ExceptionA("ExceptionA");
    }

    static void methodB() throws ExceptionB {
        System.out.println("methodB invoke!");
        try {
            methodA();
        } catch (ExceptionA exceptionA) {
            throw new ExceptionB(exceptionA);
        }
    }

    public static void main(String[] args) {
        try {
            methodB();
        } catch (ExceptionB exceptionB) {
            exceptionB.printStackTrace();
        }


    }
}
