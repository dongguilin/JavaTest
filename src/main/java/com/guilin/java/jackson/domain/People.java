package com.guilin.java.jackson.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Created by guilin on 2017/3/14.
 */
@JacksonXmlRootElement(localName = "data")
public class People {

    public People() {
    }

    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @JacksonXmlProperty(localName = "Name")
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
