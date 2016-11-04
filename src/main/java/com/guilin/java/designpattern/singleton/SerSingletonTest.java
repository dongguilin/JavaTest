package com.guilin.java.designpattern.singleton;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;

/**
 * Created by T57 on 2016/8/21 0021.
 */
public class SerSingletonTest {

    @Test
    public void test1() {
        Assert.assertEquals(SerSingleton.getInstance(), SerSingleton.getInstance());
    }

    @Test
    public void test2() throws IOException, ClassNotFoundException {
        SerSingleton serSingleton = null;

        SerSingleton serSingleton2 = SerSingleton.getInstance();
        String path = SerSingletonTest.class.getClassLoader().getResource("").getPath();
        File file = new File(path + "/sersingleton.txt");
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
        outputStream.writeObject(serSingleton2);
        outputStream.close();

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
        serSingleton = (SerSingleton) inputStream.readObject();

        Assert.assertEquals(serSingleton2, serSingleton);
    }
}
