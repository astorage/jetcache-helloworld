package com.java.jetcache.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Boris
 * @date 2020/4/8 15:48
 */
@RestController
public class TestController {

    @RequestMapping("/test/com")
    public String get() {
        return "test success";
    }
}
