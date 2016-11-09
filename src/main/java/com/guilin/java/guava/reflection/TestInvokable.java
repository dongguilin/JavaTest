package com.guilin.java.guava.reflection;

import com.google.common.reflect.Invokable;
import com.google.common.reflect.TypeToken;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertTrue;

/**
 * Created by guilin on 2016/11/4.
 */
public class TestInvokable {

    @Test
    public void testMethodPublic() throws Exception {
        Method sayMethod = Person.class.getMethod("say");
        assertTrue(Modifier.isPublic(sayMethod.getClass().getModifiers()));


        Invokable invokable = new TypeToken<Person>() {
        }.method(sayMethod);
        assertTrue(invokable.isPublic());

    }

    static class Person {
        private String name;
        private int age;
        int weight;
        public float height;

        public String say() {
            System.out.println("hello");
            return "hello";
        }
    }
}
