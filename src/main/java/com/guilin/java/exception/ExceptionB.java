package com.guilin.java.exception;

/**
 * Created by guilin1 on 15/4/2.
 */
public class ExceptionB extends Exception {

    public ExceptionB() {

    }

    public ExceptionB(String message) {
        super(message);
    }

    public ExceptionB(Throwable throwable) {
        super(throwable);
    }

    public ExceptionB(String message, Throwable throwable) {
        super(message, throwable);
    }

}
