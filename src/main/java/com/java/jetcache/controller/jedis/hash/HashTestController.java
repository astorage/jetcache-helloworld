package com.java.jetcache.controller.jedis.hash;

import com.alibaba.fastjson.JSON;
import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CreateCache;
import com.alicp.jetcache.anno.SerialPolicy;
import com.java.jetcache.model.Student;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


/**
 * @author Boris
 * @date 2020/3/12 8:53
 */
@RestController
public class HashTestController {

    @CreateCache(serialPolicy = SerialPolicy.KRYO)
    private Cache<String, Object> cache;


    @PostMapping("/test_cache_redis/hash/hincrBy")
    public Object testCacheRedis(String key, String field, Long increment) {
        JedisPool jedisPool = cache.unwrap(JedisPool.class);
        Jedis jedis = jedisPool.getResource();
        Long newValue = jedis.hincrBy(key, field, increment);
        return newValue;
    }

    @PostMapping("/test_cache_redis/hash/hincrByFloat")
    public Object testCacheRedis(Double increment) {
        JedisPool jedisPool = cache.unwrap(JedisPool.class);
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
    public Object testCacheRedis(String key, String field) {
        JedisPool jedisPool = cache.unwrap(JedisPool.class);
        Jedis jedis = jedisPool.getResource();
        String newValue = jedis.hget(key, field);
        return newValue;
    }

    /**
     * 用hget获取出来的是string
     * @param field
     * @return
     */
    @PostMapping("/test_cache_redis/hash/hset")
    public Long hset(String key, String field, String value) {
        JedisPool jedisPool = cache.unwrap(JedisPool.class);
        Jedis jedis = jedisPool.getResource();
        Long newValue = jedis.hset(key, field, value);

        return newValue;
    }

    /**
     * 用hget获取出来的是stringgra
     * @param field
     * @return
     */
    @RequestMapping(path = "/test_cache_redis/hash/hsetObj", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Long hsetObj(String key, String field, @RequestBody Student student){
        JedisPool jedisPool = cache.unwrap(JedisPool.class);
        Jedis jedis = jedisPool.getResource();
        String stuStr = JSON.toJSONString(student);
        Long newValue = jedis.hset(key, field, stuStr);
        return newValue;
    }

    /**
     * 用hget获取出来的是string
     * @param field
     * @return
     */
    @PostMapping("/test_cache_redis/hash/hgetObj")
    public Student hgetObj(String key, String field) {
        JedisPool jedisPool = cache.unwrap(JedisPool.class);
        Jedis jedis = jedisPool.getResource();
        String jsonStr = jedis.hget(key, field);
        Student student = JSON.parseObject(jsonStr, Student.class);
        return student;
    }
}
