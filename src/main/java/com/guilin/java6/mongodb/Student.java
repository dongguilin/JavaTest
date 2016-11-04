package com.guilin.java6.mongodb;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by guilin1 on 15/5/10.
 */
public class Student {

    private String id;

    private String name;

    private int age;

    private int height;

    private List<Map<String, Integer>> score;

    private Set<String> info;

    public Set<String> getInfo() {
        return info;
    }

    public void setInfo(Set<String> info) {
        this.info = info;
    }

    public Student() {
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public List<Map<String, Integer>> getScore() {
        return score;
    }

    public void setScore(List<Map<String, Integer>> score) {
        this.score = score;
    }
}
