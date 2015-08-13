package com.guilin.java6.quartz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by guilin1 on 15/5/7.
 */
public class Client {

    public static void main(String[] args) {
        System.out.println("Test start.");
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.print("Test end..");
    }
}
