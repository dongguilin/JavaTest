package com.guilin.java.jackson.xml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.guilin.java.jackson.domain.Score;
import com.guilin.java.jackson.domain.Student;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guilin on 2017/3/14.
 */
public class TestBean2Xml {

    @Test
    public void test1() throws IOException {
        Student student = buildStudent();
        ObjectMapper xmlMapper = new XmlMapper();
        String xml = xmlMapper.writeValueAsString(student);
        System.out.println(xml);
        Student student2 = xmlMapper.readValue(xml, Student.class);
        Assert.assertTrue(xml.equals(xmlMapper.writeValueAsString(student2)));
    }

    private Student buildStudent() {
        Student student = new Student();
        student.setCity("郑州");
        student.setName("xiao ming");
        List<Score> scores = new ArrayList<>();
        Score score = new Score();
        score.setName("math");
        score.setGoal(88.9f);
        scores.add(score);
        student.setScoreList(scores);

        return student;
    }

}
