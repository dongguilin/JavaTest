package com.guilin.java.jackson.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

/**
 * Created by guilin on 2017/3/14.
 */
@JacksonXmlRootElement(localName = "data")
public class Student extends People {

    private String city;

    private String school;

    @JacksonXmlElementWrapper(localName = "scores")
    @JacksonXmlProperty(localName = "score")
    private List<Score> scoreList;


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public List<Score> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<Score> scoreList) {
        this.scoreList = scoreList;
    }
}
