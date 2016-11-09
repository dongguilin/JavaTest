package com.guilin.java.guava.reflection.typetoken2;

import com.google.common.reflect.TypeToken;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by guilin on 2016/11/4.
 */
public class Client {

    @Test
    public void test() {
        Map<String, BigInteger> map1 = new HashMap<>();
        TypeToken typeToken = TypeTokenUtil.mapToken(TypeToken.of(String.class), TypeToken.of(BigInteger.class));
        System.out.println(typeToken.getRawType());
        System.out.println(typeToken.getType());


        typeToken = TypeTokenUtil.<String, BigInteger>incorrectMapToken();
        System.out.println(typeToken.getRawType());
        System.out.println(typeToken.getType());

        TypeToken<Map<?, ?>> wildMapTok = new TypeToken<Map<?, ?>>() {
        };
        System.out.println(wildMapTok.getRawType());
        System.out.println(wildMapTok.getType());
    }

    @Test
    public void test2() throws Exception {
        TypeToken<Map<String, Integer>> mapToken = new TypeToken<Map<String, Integer>>() {
        };
        TypeToken<?> entrySetToken = mapToken.resolveType(Map.class.getMethod("entrySet").getGenericReturnType());
        Assert.assertEquals("java.util.Set<java.util.Map.java.util.Map$Entry<java.lang.String, java.lang.Integer>>",
                entrySetToken.getType().toString());

    }
}
