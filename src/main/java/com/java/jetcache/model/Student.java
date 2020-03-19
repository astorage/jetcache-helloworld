package com.java.jetcache.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Boris
 * @date 2020/3/14 9:15
 */
@Data
public class Student implements Serializable {
    private Long id;

    private String name;

    private List<String> hobbies;

    private Address address;

}
