package com.guilin.java6.mongodb;

import com.mongodb.MongoClient;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.InvocationTargetException;
import java.net.UnknownHostException;
import java.util.*;

/**
 * Created by guilin1 on 15/5/10.
 */
public class StudentTest {

    private static MongoTemplate template;
    private String collectionName = "student";

    @BeforeClass
    public static void beforeClass() throws UnknownHostException {
        MongoClient client = new MongoClient("127.0.0.1", 27017);
        template = new MongoTemplate(client, "demo");
    }

    @Test
    public void testFind() {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is("S1000"));
        long start = System.currentTimeMillis();
        template.find(query,Student.class,collectionName);
//        long num = template.count(query, Student.class);
        System.out.println(System.currentTimeMillis() - start);
//        System.out.println(num);
    }

    @Test
    public void testInsert() {
        long start = System.currentTimeMillis();
        List<Student> studentList = new ArrayList<Student>();
        for (int i0 = 0; i0 < 10; i0++) {
            Student student = new Student();
            student.setId("S" + i0);
            student.setName("xiaohua" + i0);
            student.setAge(35);
            student.setHeight(155);

            Set<String> set = new HashSet<String>();
            set.add("1.0.1");
            set.add("1.0.2");
            set.add("2.0.1");
            set.add("2.0.2");

            student.setInfo(set);

            List<Map<String, Integer>> scores = new ArrayList<Map<String, Integer>>();
            for (int i = 0; i < 3; i++) {
                Map<String, Integer> score = new HashMap<String, Integer>();
                score.put("chinese", 40 + i * 2);
                score.put("math", 50 + i * 2);
                score.put("english", 60 + i * 2);
                score.put("isnull", null);
                scores.add(score);
            }
            student.setScore(scores);
            studentList.add(student);

            if (studentList.size() > 5000) {
                template.insert(studentList, collectionName);
                studentList.clear();
            }
        }
        if (studentList.size() > 0) {
            template.insert(studentList, collectionName);
            studentList.clear();
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    @Test
    public void testDelAll() throws Exception {

        Query query = new Query();
        Set<String> name = new HashSet<String>();
        for (int i = 0; i < 5000; i++) {
            name.add("S" + i);
        }
        query.addCriteria(Criteria.where("_id").in(name));

        long start = System.currentTimeMillis();
        template.remove(query, Student.class, collectionName);
        System.out.println(System.currentTimeMillis() - start);

    }


    @Test
    public void testUpdate1() {
        Query query = Query.query(Criteria.where("name").is("xiaowang"));

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("height", 100);
        map.put("age", 10);

        Update update = new Update();
        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> m = iterator.next();
            update.set(m.getKey(), m.getValue());
        }
        if (update.getUpdateObject().keySet().size() > 0) {
            template.upsert(query, update, collectionName);
//            template.upsert(query, update, Student.class, "student");
        }
    }

    @Test
    public void testUpsert() throws InvocationTargetException, IllegalAccessException {
        Query query = Query.query(Criteria.where("name").is("xiaowang1"));
        Update update = new Update();
        update.set("height", 200);
        update.set("age", 100);
        template.upsert(query, update, Student.class, collectionName);
    }

    @Test
    public void testUpdate() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Query query = Query.query(Criteria.where("name").is("xiaohua01"));
        Update update = new Update();
//        update.set("age",11);

        List<Map<String, Integer>> scores = new ArrayList<Map<String, Integer>>();
        for (int i = 0; i < 3; i++) {
            Map<String, Integer> score = new HashMap<String, Integer>();
            score.put("chinese", 10 + i * 2);
            score.put("math", 20 + i * 2);
            score.put("english", 30 + i * 2);
//            score.put("isnull", null);
            scores.add(score);
        }

        Set<String> set = new HashSet<String>();
        set.add("3.0.2");
        set.add("1.0.3");
//        set.add("2.0.1");
        set.add("2.0.5");

//        update.addToSet("info",set);
        update.set("info",set);


//        update.addToSet("score",scores);

//        update.addToSet("name");
//        update.set("name", "dfdf");
//        update.set("age", 35);

//        Student student = new Student();
//        student.setName("xiaoming");
//        update.getUpdateObject().putAll(BeanUtils.describe(student));
//        template.findAndModify(query, update, student.getClass(), "student");

//        if(update.getUpdateObject().keySet().size()>0){
//            System.out.println(update.getUpdateObject());
//            template.updateFirst(query, update,"student");
//        }

//        template.findAndModify(query,update,Student.class,"student");

//        template.updateFirst(query, update, Student.class, collectionName);
        template.upsert(query, update, Student.class, collectionName);

//        template.updateFirst(query, update, Student.class,"student");


    }


}
