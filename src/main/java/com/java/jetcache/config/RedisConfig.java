package com.java.jetcache.config;

import com.alicp.jetcache.redis.RedisCache;
import com.alicp.jetcache.redis.RedisCacheConfig;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * @author Boris
 * @date 2020/3/11 19:42
 */
@Configuration
public class RedisConfig {


    /**
     * TODO 这个还需要研究一下
     * @return
     */
    @Bean
    public RedisCache redisCache() {
        RedisCacheConfig  config = new RedisCacheConfig();
        config.setKeyPrefix("tr_");
        JedisPool jedisPool = new JedisPool();
        config.setJedisPool(jedisPool);
        return new RedisCache(config);
    }


//    public void test() {
//        GenericObjectPoolConfig pc = new GenericObjectPoolConfig();
//        pc.setMinIdle(2);
//        pc.setMaxIdle(10);
//        pc.setMaxTotal(10);
//        return new JedisPool(pc, "localhost", 6379);
//
//    }




}
