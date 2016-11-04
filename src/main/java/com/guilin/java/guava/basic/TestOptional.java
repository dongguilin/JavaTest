package com.guilin.java.guava.basic;

import com.google.common.base.Optional;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;

/**
 * Created by guilin on 2016/9/30.
 */
public class TestOptional {

    //Optional.of(T) 创建指定引用的Optional实例，若引用为null则快速失败
    @Test
    public void testOf() {
        Optional<Integer> possible = Optional.of(5);
        assertThat(possible.get(), is(5));//返回Optional所包含的引用，若引用缺失，则抛出java.lang.IllegalStateException
        assertThat(possible.isPresent(), is(true));//如果Optional包含非null的引用（引用存在），返回true
        assertThat(possible.or(-1), is(5));//返回Optional所包含的引用，若引用缺失，返回指定的值
        assertThat(possible.orNull(), is(5));//返回Optional所包含的引用，若引用缺失，返回null

        try {
            Optional.of(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
        }
    }

    //Optional.absent() 创建引用缺失的Optional实例
    @Test
    public void testAbsent() {
        Optional<Integer> optional = Optional.absent();
        assertThat(optional.isPresent(), is(false));

        try {
            assertThat(optional.or(-1), is(-1));//返回Optional所包含的引用，若引用缺失，返回指定的值
            assertThat(optional.orNull() == null, is(true));//返回Optional所包含的引用，若引用缺失，返回null
            optional.get();//返回Optional所包含的引用，若引用缺失，则抛出java.lang.IllegalStateException
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            assertThat(e.getMessage(), endsWith("cannot be called on an absent value"));
        }
    }

    //Optional.fromNullable(T) 创建指定引用的Optional实例，若引用为null则表示缺失
    @Test
    public void testFromNullable() {
        Optional<Integer> optional = Optional.fromNullable(5);
        assertThat(optional.isPresent(), is(true));
        assertThat(optional.get(), is(5));

        optional = Optional.fromNullable(null);
        assertThat(optional.isPresent(), is(false));

        try {
            optional.get();
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            assertThat(e.getMessage(), endsWith("cannot be called on an absent value"));
        }

        System.out.println(Optional.fromNullable(null).or(-1));
    }

}
