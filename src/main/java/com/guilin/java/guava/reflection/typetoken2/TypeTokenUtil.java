package com.guilin.java.guava.reflection.typetoken2;

import com.google.common.reflect.TypeParameter;
import com.google.common.reflect.TypeToken;

import java.util.Map;

/**
 * Created by guilin on 2016/11/4.
 */
public class TypeTokenUtil {

    public static <K, V> TypeToken<Map<K, V>> mapToken(TypeToken<K> keyToken, TypeToken<V> valueToken) {
        return new TypeToken<Map<K, V>>() {
        }.where(new TypeParameter<K>() {
        }, keyToken).where(new TypeParameter<V>() {
        }, valueToken);
    }

    public static <K, V> TypeToken<Map<K, V>> incorrectMapToken() {
        return new TypeToken<Map<K, V>>() {
        };
    }
}
