package com.guilin.java6.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.File;

/**
 * Created by guilin on 2014/4/18.
 */
public class Dom4jTest {

    @Test
    public void test1() throws DocumentException {
        Document doc = null;
        SAXReader reader = new SAXReader();
        File file = new File("");
        reader.read(file);
    }
}
