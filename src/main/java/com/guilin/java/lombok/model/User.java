package com.guilin.java.lombok.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by guilin on 2017/4/24.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class User {
    private String name;
    private int age;
    private String address;
    private boolean isMale;

    public static void main(String[] args) {
        log.info("hello");
    }
}
