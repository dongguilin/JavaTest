package com.guilin.java6.collection;

import org.junit.Test;

import java.util.Iterator;
import java.util.Stack;

public class StackTest {

    @Test
    public void test1() {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(2);
        stack.push(12);
        stack.push(1);
        stack.push(-1);
        stack.push(5);
        for (int i : stack) {
            System.out.print(i + " ");//先进先出
        }
        System.out.println();
        Iterator<Integer> iter = stack.iterator();
        while (iter.hasNext()) {
            System.out.print(iter.next() + "　");
        }
    }
}
