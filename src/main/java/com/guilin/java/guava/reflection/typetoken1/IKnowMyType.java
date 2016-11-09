package com.guilin.java.guava.reflection.typetoken1;

import com.google.common.reflect.TypeToken;

/**
 * Created by guilin on 2016/11/4.
 */
public abstract class IKnowMyType<T> {
    TypeToken<T> type = new TypeToken<T>(getClass()) {
    };
}
