package com.java.jetcache;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCreateCacheAnnotation
public class JetcacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(JetcacheApplication.class, args);
    }

}
