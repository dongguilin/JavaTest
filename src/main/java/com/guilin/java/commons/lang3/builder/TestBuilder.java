package com.guilin.java.commons.lang3.builder;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Administrator on 2017/4/23.
 */
public class TestBuilder {

    @Test
    public void test1() {
        Staff staff1 = new Staff(123, "John Smith", new Date());
        Staff staff2 = new Staff(456, "Jane Smith", new Date());

        System.out.println("staff1's info: " + staff1);
        System.out.println("staff2's info: " + staff2);
        System.out.println("staff1's hash code: " + staff1.hashCode());
        System.out.println("staff2's hash code: " + staff2.hashCode());
        assertThat(staff1.equals(staff2), is(false));

        Staff staff3 = new Staff();
        staff3.setStaffId(staff2.getStaffId());
        assertThat(staff2.equals(staff3), is(true));
    }

    @Test
    public void test2() {
        Staff2 staff1 = new Staff2(123, "John Smith", new Date());
        Staff2 staff2 = new Staff2(456, "Jane Smith", new Date());

        System.out.println("staff1's info: " + staff1);
        System.out.println("staff2's info: " + staff2);
        System.out.println("staff1's hash code: " + staff1.hashCode());
        System.out.println("staff2's hash code: " + staff2.hashCode());
        assertThat(staff1.equals(staff2), is(false));

        Staff2 staff3 = new Staff2();
        staff3.setStaffId(staff2.getStaffId());
        staff3.setDateJoined(staff2.getDateJoined());
        staff3.setStaffName(staff2.getStaffName());
        assertThat(staff2.equals(staff3), is(true));

        staff3.setStaffName("jack");
        assertThat(staff2.equals(staff3), is(false));
    }
}
