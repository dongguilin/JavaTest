package com.guilin.java.oop;

/**
 * Created by hadoop on 2015/9/27.
 */
public class Person {

    private String name;

    private int age;

    private int getAge() {
        return age;
    }

    private void setAge(int age) {
        this.age = age;
    }

    public boolean bigger(Person age) {
        return this.age > age.age;
    }

    public static void main(String[] args) {
        Person person = new Person();
        person.setAge(11);

        Person person2 = new Person();
        person2.setAge(12);

        System.out.println(person.bigger(person2));
    }


}
