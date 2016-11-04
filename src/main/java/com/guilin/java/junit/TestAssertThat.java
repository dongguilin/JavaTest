package com.guilin.java.junit;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import uk.co.datumedge.hamcrest.json.SameJSONAs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.CombinableMatcher.both;
import static org.hamcrest.core.CombinableMatcher.either;
import static org.hamcrest.core.Every.everyItem;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by guilin on 2016/9/29.
 */
public class TestAssertThat {

    //语法：assertThat([value], [matcher statement]);
    @Test
    public void testAssertThat() {
        int x = 3;
        assertThat(x, is(3));
        assertThat(x, is(not(4)));

        String responseString = "color colour hello";
        assertThat(responseString, either(containsString("color")).or(containsString("colour")));
        assertTrue(responseString.contains("color") || responseString.contains("colour"));
        assertThat(responseString, anyOf(containsString("color"), containsString("colour")));

        List<String> myList = new ArrayList<>();
        myList.add("1");
        myList.add("3");
        assertThat(myList, hasItem("3"));
    }

    @Test
    public void testJSON() {
        assertThat(
                "{\"age\":43, \"friend_ids\":[16, 52, 23]}",
                SameJSONAs.sameJSONAs("{\"friend_ids\":[52, 23, 16]}")
                        .allowingExtraUnexpectedFields()
                        .allowingAnyArrayOrdering());

    }

    // JUnit Matchers assertThat
    @Test
    public void testAssertThatBothContainsString() {
        MatcherAssert.assertThat("albumen", both(containsString("a")).and(containsString("b")));
    }

    @Test
    public void testAssertThatHasItems() {
        MatcherAssert.assertThat(Arrays.asList("one", "two", "three"), hasItems("one", "three"));
    }

    @Test
    public void testAssertThatEveryItemContainsString() {
        MatcherAssert.assertThat(Arrays.asList(new String[]{"fun", "ban", "net"}), everyItem(containsString("n")));
    }


}
