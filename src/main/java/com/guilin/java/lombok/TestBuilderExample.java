package com.guilin.java.lombok;

import com.google.common.collect.Lists;
import com.guilin.java.lombok.model.BuilderExample;
import org.junit.Test;

import java.util.List;

/**
 * Created by guilin on 2017/4/24.
 */
public class TestBuilderExample {

    /**
     * @Singular can only be applied to collection types known to lombok. Currently, the supported types are:
     * <p>
     * java.util:
     * Iterable, Collection, and List (backed by a compacted unmodifiable ArrayList in the general case).
     * Set, SortedSet, and NavigableSet (backed by a smartly sized unmodifiable HashSet or TreeSet in the general case).
     * Map, SortedMap, and NavigableMap (backed by a smartly sized unmodifiable HashMap or TreeMap in the general case).
     * Guava's com.google.common.collect:
     * ImmutableCollection and ImmutableList (backed by the builder feature of ImmutableList).
     * ImmutableSet and ImmutableSortedSet (backed by the builder feature of those types).
     * ImmutableMap, ImmutableBiMap, and ImmutableSortedMap (backed by the builder feature of those types).
     * ImmutableTable (backed by the builder feature of ImmutableTable).
     */
    @Test
    public void test1() {
        BuilderExample example = BuilderExample.builder()
                .name("lili").age(11).build();
        System.out.println(example);
        System.out.println(BuilderExample.builder().occupation("hehe").build());

        List<String> list = Lists.newArrayList("hello", "world");
        example = BuilderExample.builder().set1s(list).build();
        System.out.println(example);
        System.out.println(example.getSet1s());

    }
}
