package com.guilin.java6.exception;

/**
 * Created by guilin1 on 15/4/2.
 */
public class ExceptionA extends Exception {

    public ExceptionA() {

    }

    public ExceptionA(String message) {
        super(message);
    }

    public ExceptionA(Throwable throwable) {
        super(throwable);
    }

    public ExceptionA(String message, Throwable throwable) {
        super(message, throwable);
    }


}
