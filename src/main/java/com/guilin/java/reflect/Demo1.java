package com.guilin.java.reflect;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * Created by guilin1 on 15/10/24.
 */
public class Demo1<T> {

    @Test
    public void test1() {
        Class clazz = new Demo1<Student>().getGenericClass(0);
        System.out.println(clazz);
    }

    @JsonRootName("details")
    class Student {
        private String name;
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }

    public Class<T> getGenericClass(int i) {
        System.out.println(this.getClass());
        System.out.println(this.getClass().getSuperclass());
        Type genType = this.getClass().getGenericSuperclass();
        Type[] args = ((ParameterizedType) genType).getActualTypeArguments();
        if (i > -1 && i < args.length) {
            if (args[i] instanceof TypeVariable) {
                TypeVariable t = (TypeVariable) args[i];
                return (Class) t.getGenericDeclaration();
            } else {
                return (Class) args[i];
            }
        } else {
            return null;
        }
    }

}
