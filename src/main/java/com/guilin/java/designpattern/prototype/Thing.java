package com.guilin.java.designpattern.prototype;

import java.util.ArrayList;

/**
 * Created by T57 on 2016/11/9 0009.
 */
public class Thing implements Cloneable {

    private ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected Thing clone() throws CloneNotSupportedException {
        Thing thing = null;
        try {
            thing = (Thing) super.clone();
            //深拷贝
            thing.arrayList = (ArrayList<String>) this.arrayList.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return thing;
    }

    public void setValue(String value) {
        this.arrayList.add(value);
    }

    public ArrayList<String> getValue() {
        return this.arrayList;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Thing thing = new Thing();
        thing.setValue("张三");
        Thing cloneThing = thing.clone();
        cloneThing.setValue("李四");
        System.out.println(thing.getValue());
        System.out.println(cloneThing.getValue());

    }
}
