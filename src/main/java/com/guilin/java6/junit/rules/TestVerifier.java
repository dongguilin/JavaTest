package com.guilin.java6.junit.rules;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Verifier;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.experimental.results.PrintableResult.testResult;
import static org.junit.experimental.results.ResultMatchers.isSuccessful;

/**
 * Created by guilin on 2016/9/30.
 * //TODO ???
 * Verifier is a base class for Rules like ErrorCollector, which can turn otherwise passing test methods into failing tests if a verification check is failed
 */
public class TestVerifier {
    private static String sequence;

    @Rule
    public Verifier collector = new Verifier() {
        @Override
        protected void verify() {
            sequence += "verify ";
        }
    };

    @Test
    public void example() {
        sequence += "test ";
    }

    @Test
    public void verifierRunsAfterTest() {
        sequence = "";
        assertThat(testResult(TestVerifier.class), isSuccessful());
        assertEquals("test verify ", sequence);
    }

}
