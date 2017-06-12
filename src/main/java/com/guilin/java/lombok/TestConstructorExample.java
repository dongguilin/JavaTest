package com.guilin.java.lombok;

import com.guilin.java.lombok.model.ConstructorExample;
import org.junit.Test;

/**
 * Created by guilin on 2017/4/24.
 */
public class TestConstructorExample {

    @Test
    public void test1() {
        ConstructorExample example = new ConstructorExample(2, 3, 11);
        System.out.println(example);

        ConstructorExample.NoArgsExample noArgsExample = new ConstructorExample.NoArgsExample();
        System.out.println(noArgsExample);
    }
}
