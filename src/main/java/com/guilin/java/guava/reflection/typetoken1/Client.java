package com.guilin.java.guava.reflection.typetoken1;

import com.google.common.reflect.TypeToken;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by guilin on 2016/11/4.
 */
public class Client {

    @Test
    public void test1() {
        TypeToken<Map<String, String>> typeToken = new IKnowMyType<Map<String, String>>() {
        }.type;
        Assert.assertEquals("interface java.util.Map", typeToken.getRawType().toString());
        Assert.assertEquals("java.util.Map<java.lang.String, java.lang.String>", typeToken.getType().toString());

        TypeToken<HashMap<Integer, String>> typeToken2 = new IKnowMyType<HashMap<Integer, String>>() {
        }.type;
        Assert.assertEquals("class java.util.HashMap", typeToken2.getRawType().toString());
        Assert.assertEquals("java.util.HashMap<java.lang.Integer, java.lang.String>", typeToken2.getType().toString());

        TypeToken<String> typeToken3 = new IKnowMyType<String>() {
        }.type;
        Assert.assertEquals("class java.lang.String", typeToken3.getRawType().toString());
        Assert.assertEquals("class java.lang.String", typeToken3.getType().toString());

        TypeToken<List<Person>> typeToken4 = new IKnowMyType<List<Person>>() {
        }.type;
        Assert.assertEquals("interface java.util.List", typeToken4.getRawType().toString());
        Assert.assertEquals("java.util.List<com.guilin.java.guava.reflection.typetoken1.Client$Person>", typeToken4.getType().toString());

        TypeToken<Person> typeToken5 = new IKnowMyType<Person>() {
        }.type;
        Assert.assertEquals("class com.guilin.java.guava.reflection.typetoken1.Client$Person", typeToken5.getRawType().toString());
        Assert.assertEquals("class com.guilin.java.guava.reflection.typetoken1.Client$Person", typeToken5.getType().toString());

    }

    static class Person {
        private String name;
        private int age;
        private String country;

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

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }
}
