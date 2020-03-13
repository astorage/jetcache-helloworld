package com.java.jetcache.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Boris
 * @date 2020/3/12 16:42
 */

//@ConfigurationProperties(prefix = "jetcache.remote.default")
public class ParamConfig {

    private String host;

    private Integer port;


}
