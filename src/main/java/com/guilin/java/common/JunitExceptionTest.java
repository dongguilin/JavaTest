package com.guilin.java.common;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created with IntelliJ IDEA.
 * User: guilin
 * Date: 14-4-9
 * Time: 下午4:11
 * To change this template use File | Settings | File Templates.
 */
public class JunitExceptionTest {

    /**
     * 旧的方式，出错时，信息不全，不推荐使用
     */
    @Test
    public void testOldPattern() {
        Throwable t = null;
        try {
            int[] a = new int[10];
            System.out.println(a[11]);
            fail("no exception throw");
        } catch (Exception ex) {
            t = ex;
        }
        assertNotNull(t);
        assertTrue(t instanceof IndexOutOfBoundsException);
//        assertTrue(t.getMessage().contains("hehe"));
    }

    @Test(expected = NullPointerException.class)
    public void testNewPattern1() {
        File file = null;
        System.out.println(file.canRead());
    }

    @Rule
    public ExpectedException exceptedException = ExpectedException.none();

    @Test
    public void testNewPattern2() {
        exceptedException.expect(NullPointerException.class);
//        exceptedException.expectMessage("hehe");
        File file = null;
        System.out.println(file.canRead());
    }

}
