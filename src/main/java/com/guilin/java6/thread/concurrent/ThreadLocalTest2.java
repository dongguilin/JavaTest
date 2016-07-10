package com.guilin.java6.thread.concurrent;

import java.util.Random;

/**
 * ThreadLocal典型应用，HibernateUtil获取Session
 *
 * @author Administrator
 */
public class ThreadLocalTest2 implements Runnable {

    private static final ThreadLocal<Student> threadLocal = new ThreadLocal<Student>();

    public static void main(String[] args) {

        ThreadLocalTest2 test1 = new ThreadLocalTest2();
        new Thread(test1, "a").start();
        new Thread(test1, "b").start();

    }

    @Override
    public void run() {
        // 获取当前线程的名字
        String currentThreadName = Thread.currentThread().getName();
        System.out.println(currentThreadName + " is running!");
        // 产生一个随机数并打印
        Random random = new Random();
        int age = random.nextInt(100);
        System.out
                .println("thread " + currentThreadName + " set age to:" + age);
        // 获取一个Student对象，并将随机数年龄插入到对象属性中
        Student student = getStudent();
        student.setAge(age);
        System.out.println("thread " + currentThreadName
                + " first read age is:" + student.getAge());
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("thread " + currentThreadName
                + " second read age is:" + student.getAge());
    }

    public Student getStudent() {
        Student stu = threadLocal.get();
        if (stu == null) {
            stu = new Student();
            threadLocal.set(stu);
        }
        return stu;
    }

}

class Student {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
