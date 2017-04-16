package com.guilin.java.gc;

import javassist.ClassPool;
import javassist.CtClass;
import org.junit.Test;

/**
 * Created by guilin on 2017/3/28.
 */
public class TestHeapGC {

    //-XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -Xms40M -Xmx40M -Xmn20M
    @Test
    public void test1() {
        byte[] b1 = new byte[1024 * 1024 / 2];
        byte[] b2 = new byte[1024 * 1024 * 8];
        b2 = null;
        b2 = new byte[1024 * 1024 * 8];//进行一次新生代GC
    }

    //-XX:PermSize=2M -XX:MaxPermSize=4M -XX:+PrintGCDetails
    @Test
    public void testConstantPool() {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String t = String.valueOf(i).intern();
        }
    }

    //-XX:PermSize=2M -XX:MaxPermSize=4M -XX:+PrintGCDetails
    @Test
    public void testOneClassLoad() throws Exception {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            CtClass c = ClassPool.getDefault().makeClass("Geym" + i);//定义类名
            c.setSuperclass(ClassPool.getDefault().get("com.guilin.java.gc.JavaBeanObject"));
            Class clz = c.toClass();
            JavaBeanObject v = (JavaBeanObject) clz.newInstance();//新建类
        }
    }
}
