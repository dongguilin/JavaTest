package com.guilin.java6.exception;

import java.nio.charset.UnmappableCharacterException;

public class Father {

    protected void m1() throws UnmappableCharacterException {
        System.out.println("Father m1 throws UnmappableCharacterException");
    }

    protected void m2() throws ArrayIndexOutOfBoundsException {
        System.out.println("Father m2 throws ArrayIndexOutOfBoundsException");
    }

}
