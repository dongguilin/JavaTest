package com.guilin.java.lombok.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Created by guilin on 2017/4/24.
 */
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor
public class ConstructorExample<T> {
    private int x, y;
    @NonNull
    private T description;

    @NoArgsConstructor
    public static class NoArgsExample {
        @NonNull
        private String field;
    }
}
