package com.java.jetcache.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Boris
 * @date 2020/3/14 9:17
 */
@Data
public class Address implements Serializable {

    private Long id;

    private String address;

    private String mobile;

    private String mail;
}
