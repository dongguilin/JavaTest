package com.guilin.java.collection;

import org.junit.Test;

import java.util.*;

public class MapTest {

    @Test
    public void test1() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("", 1);
        map.put("hehe", 22);
        map.put(null, -1);
        map.put("hehe", 23);
        Iterator<String> iter = map.keySet().iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            System.out.println(key + ":" + map.get(key));
        }
    }

    class Dept {
        private int id;
        private int pareId;
        private List<Dept> children;

        Dept() {
        }

        Dept(int id, int pareId) {
            this.id = id;
            this.pareId = pareId;
            this.children = new ArrayList<Dept>();
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPareId() {
            return pareId;
        }

        public void setPareId(int pareId) {
            this.pareId = pareId;
        }

        public List<Dept> getChildren() {
            return children;
        }

        public void setChildren(List<Dept> children) {
            this.children = children;
        }
    }

    @Test
    public void test2() {

        Map<Integer, Dept> maps = new HashMap<Integer, Dept>();

        List<Dept> list = new ArrayList<Dept>();
        list.add(new Dept(21, 2));
        list.add(new Dept(2, -1));
        list.add(new Dept(22, 2));
        list.add(new Dept(1, -1));
        list.add(new Dept(2211, 221));
        list.add(new Dept(221, 22));
        list.add(new Dept(223, 22));
        list.add(new Dept(222, 22));
        list.add(new Dept(23, 2));
        list.add(new Dept(2212, 221));

        for (Dept dept : list) {
            int key = dept.getId();
            int pareId = dept.getPareId();
            if (!maps.containsKey(key)) {
                maps.put(key, dept);
            }
            if (maps.containsKey(pareId)) {//dept为子节点
                maps.get(pareId).getChildren().add(dept);
            }
        }
        System.out.println(maps.size());

        List<Dept> result = new ArrayList<Dept>();
        Iterator iter = maps.values().iterator();
        while (iter.hasNext()) {
            Dept dept = (Dept) iter.next();
            if (dept.getPareId() == -1) {
                result.add(dept);
            }
        }

        printList(result);


    }

    private void printList(List<Dept> list) {
        for (Dept dept : list) {
//            String prefix=dept.getPareId()+"_";
            System.out.println(dept.getId() + "," + dept.getPareId());
            List<Dept> children = dept.getChildren();
            printList(children);
        }

    }


}
