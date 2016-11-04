package com.guilin.java.log4j;

import org.apache.log4j.Logger;

/**
 * Created by guilin on 2014/4/18.
 */
public class Log4jTest {

    private Logger logger = Logger.getLogger(Log4jTest.class);

    public void say(String str) {
        logger.debug(str);
    }

    public static void main(String[] args) {
        Log4jTest log4jTest = new Log4jTest();
        log4jTest.say("Hello World!");
    }
}
