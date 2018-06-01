package com.guilin.java.java8;

import org.junit.Assert;
import org.junit.Test;

import java.lang.annotation.*;

/**
 * 重复注解
 * <p>
 * 在Java 8中使用@Repeatable注解定义重复注解，实际上，这并不是语言层面的改进，而是编译器做的一个trick，底层的技术仍然相同。
 */
public class TestRepeatingAnnotations {

    @Test
    public void test1() {
        for (Filter filter : Filterable.class.getAnnotationsByType(Filter.class)) {
            System.out.println(filter.value());
        }

        Assert.assertEquals(2, Filterable.class.getAnnotationsByType(Filters.class)[0].value().length);

    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Filters {
        Filter[] value();
    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Repeatable(Filters.class)
    public @interface Filter {
        String value();
    }

    ;

    @Filter("filter1")
    @Filter("filter2")
    public interface Filterable {
    }
}
