package com.guilin.java.common;

import com.guilin.java.common.Name.New4;

public class Client {

    public static void main(String[] args) {
        Name name = new Name();
        New4 new4 = name.new New4();
        System.out.println(new4.a + " " + new4.b);
        new4.a();
    }

}
