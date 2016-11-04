package com.guilin.java.junit;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by guilin on 2016/9/29.
 */
public class TestException {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldTestExceptionMessage() throws IndexOutOfBoundsException {
        List<Object> list = new ArrayList<Object>();

        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("Index: 0, Size: 0");
        list.get(0); // execution will never get past this line
    }

    @Test
    public void byzero() {
        thrown.equals(ArithmeticException.class);
//        thrown.expectMessage("/ by zero");
        thrown.expectMessage(startsWith("/"));
        int a = 1 / 0;
    }

    @Test
    public void testExceptionMessage() {
        try {
            new ArrayList<Object>().get(0);
            fail("Expected an IndexOutOfBoundsException to be thrown");
        } catch (IndexOutOfBoundsException anIndexOutOfBoundsException) {
            assertThat(anIndexOutOfBoundsException.getMessage(), is("Index: 0, Size: 0"));
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void empty() {
        new ArrayList<Object>().get(0);
    }
}
