package com.guilin.java.lombok.model;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Created by guilin on 2017/4/24.
 */
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor
public class People {

    @NonNull
    private String id;

    @NonNull
    private String name;

    private String age;
}
