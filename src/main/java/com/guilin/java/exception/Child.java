package com.guilin.java.exception;

import java.io.IOError;
import java.nio.charset.UnmappableCharacterException;

public class Child extends Father {

    @Override
    protected void m1() throws UnmappableCharacterException, RuntimeException,
            ArrayIndexOutOfBoundsException, Error, IOError {
        super.m1();
        System.out.println("Child m1 throws UnmappableCharacterException,"
                + " RuntimeException, ArrayIndexOutOfBoundsException, "
                + "Error, IOError");
    }

    /**
     * 对于RuntimeException 1.子类可抛出RuntimeException异常及其子异常; 2.子类可抛出Error及其子类
     */
    @Override
    protected void m2() throws ArrayIndexOutOfBoundsException,
            IndexOutOfBoundsException, NullPointerException, Error, IOError {
        super.m2();
        System.out.println("Child m2 throws ArrayIndexOutOfBoundsException，"
                + "IndexOutOfBoundsException,NullPointerException,"
                + " Error, IOError ");
    }

    public static void main(String[] args) throws Exception {
        Child child = new Child();
        child.m1();
        child.m2();

    }

}
