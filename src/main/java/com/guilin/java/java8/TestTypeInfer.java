package com.guilin.java.java8;

import org.junit.Test;

/**
 * <pre>
 *  更好的类型推断
 *
 *  Java 8编译器在类型推断方面有很大的提升，在很多场景下编译器可以推导出某个参数的数据类型，从而使得代码更为简洁
 * </pre>
 */
public class TestTypeInfer {

    @Test
    public void test1() {

        // 参数Value.defaultValue()的类型由编译器推导得出，不需要显式指明。在Java 7中这段代码会有编译错误，除非使用Value.<String>defaultValue()
        Value<String> value = new Value<>();
        value.getOrDefault("22", Value.defaultValue());
    }

    public static class Value<T> {
        public static <T> T defaultValue() {
            return null;
        }

        public T getOrDefault(T value, T defaultValue) {
            return (value != null) ? value : defaultValue;
        }
    }
}
