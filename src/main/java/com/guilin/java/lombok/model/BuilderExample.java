package com.guilin.java.lombok.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import lombok.ToString;

import java.util.Set;

/**
 * Created by guilin on 2017/4/24.
 */
@Builder
@ToString
@Data
public class BuilderExample {
    @Builder.Default
    private long created = System.currentTimeMillis();
    private String name;
    private int age;
    @Singular
    private Set<String> occupations;

    @Singular
    private ImmutableSet set1s;

    @Singular("set2s")
    private ImmutableList set2s;

}
