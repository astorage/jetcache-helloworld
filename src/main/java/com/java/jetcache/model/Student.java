package com.java.jetcache.model;

import lombok.Data;

import java.util.List;

/**
 * @author Boris
 * @date 2020/3/14 9:15
 */
@Data
public class Student {
    private Long id;

    private String name;

    private List<String> hobbies;

    private Address address;

}
