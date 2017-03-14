package com.guilin.java.jackson.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Created by guilin on 2017/3/14.
 */
@JacksonXmlRootElement(localName = "data")
public class Score {
    private String name;
    private float goal;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getGoal() {
        return goal;
    }

    public void setGoal(float goal) {
        this.goal = goal;
    }
}
