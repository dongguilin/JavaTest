package com.guilin.java.jodd;

import jodd.io.FileUtil;
import junit.framework.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

/**
 * Created by guilin on 2014/4/14.
 */
public class FileUtilTest {

    @Test
    public void testCopyFile() throws IOException {
        URL url = this.getClass().getResource("");
        String src = url.getPath() + "FileUtilTest.class";
        String dest = url.getPath() + "CopyFileUtilTest.txt";
        FileUtil.copy(src, dest);
        boolean flag = FileUtil.compare(src, dest);
        Assert.assertEquals(true, flag);
    }
}
