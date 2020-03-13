package com.java.jetcache.controller.jedis.hash;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CreateCache;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;



/**
 * @author Boris
 * @date 2020/3/12 8:53
 */
@RestController
public class HashTestController {

    @CreateCache
    private Cache<String, Object> cache;


    @PostMapping("/test_cache_redis/hash/hincrBy")
    public Object testCacheRedis(Long increment) {
        JedisPool jedisPool = cache.unwrap(JedisPool.class);
        //JedisPool jedisPool = (JedisPool) redisCache.unwrap();
        Jedis jedis = jedisPool.getResource();
        Long newValue = jedis.hincrBy("testhashincrement", "f1", increment);
        return newValue;
    }

    @PostMapping("/test_cache_redis/hash/hincrByFloat")
    public Object testCacheRedis(Double increment) {
        JedisPool jedisPool = cache.unwrap(JedisPool.class);
        //JedisPool jedisPool = (JedisPool) redisCache.unwrap();
        Jedis jedis = jedisPool.getResource();
        Double newValue = jedis.hincrByFloat("testhashincrement", "f2", increment);
        return newValue;
    }

    /**
     * 用hget获取出来的是string
     * @param field
     * @return
     */
    @PostMapping("/test_cache_redis/hash/hget")
    public Object testCacheRedis(String field) {
        JedisPool jedisPool = cache.unwrap(JedisPool.class);
        //JedisPool jedisPool = (JedisPool) redisCache.unwrap();
        Jedis jedis = jedisPool.getResource();
        String newValue = jedis.hget("testhashincrement", "f2");
        return newValue;
    }
}
