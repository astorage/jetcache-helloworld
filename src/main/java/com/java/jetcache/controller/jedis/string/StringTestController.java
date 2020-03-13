package com.java.jetcache.controller.jedis.string;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CreateCache;
import com.alicp.jetcache.anno.SerialPolicy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.BitOP;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * @author Boris
 * @date 2020/3/13 16:52
 */
@RestController
public class StringTestController {

    @CreateCache(serialPolicy = SerialPolicy.JAVA)
    private Cache<String, Object> cache;

    @RequestMapping("/jetcache/jedis/set")
    public String set(String key, String value) {
        JedisPool jedisPool = cache.unwrap(JedisPool.class);
        Jedis jedis = jedisPool.getResource();
        String returnCode = jedis.set(key, value);
        return returnCode;
    }

    @RequestMapping("/jetcache/jedis/setnx")
    public Long setnx(String key, String value) {
        JedisPool jedisPool = cache.unwrap(JedisPool.class);
        Jedis jedis = jedisPool.getResource();
        Long returnCode = jedis.setnx(key, value);
        return returnCode;
    }


    @RequestMapping("/jetcache/jedis/append")
    public Long append(String key, String value) {
        JedisPool jedisPool = cache.unwrap(JedisPool.class);
        Jedis jedis = jedisPool.getResource();
        Long returnCode = jedis.append(key, value);
        return returnCode;
    }

    @RequestMapping("/jetcache/jedis/bitcount")
    public Long bitcount(String key) {
        JedisPool jedisPool = cache.unwrap(JedisPool.class);
        Jedis jedis = jedisPool.getResource();
        Long returnCode = jedis.bitcount(key);
        return returnCode;
    }

    @RequestMapping("/jetcache/jedis/bitop")
    public Long bitop(String destKey, String ... keys) {
        JedisPool jedisPool = cache.unwrap(JedisPool.class);
        Jedis jedis = jedisPool.getResource();
        Long returnCode = jedis.bitop(BitOP.AND, destKey, keys);
        return returnCode;
    }

    @RequestMapping("/jetcache/jedis/decr")
    public Long decr(String key) {
        JedisPool jedisPool = cache.unwrap(JedisPool.class);
        Jedis jedis = jedisPool.getResource();
        Long returnCode = jedis.decr(key);
        return returnCode;
    }

    @RequestMapping("/jetcache/jedis/decrBy")
    public Long decrBy(String key, long decrement) {
        JedisPool jedisPool = cache.unwrap(JedisPool.class);
        Jedis jedis = jedisPool.getResource();
        Long returnCode = jedis.decrBy(key, decrement);
        return returnCode;
    }

    @RequestMapping("/jetcache/jedis/get")
    public String decrBy(String key) {
        JedisPool jedisPool = cache.unwrap(JedisPool.class);
        Jedis jedis = jedisPool.getResource();
        String value = jedis.get(key);
        return value;
    }

    @RequestMapping("/jetcache/jedis/getbit")
    public Boolean getbit(String key, long offset) {
        JedisPool jedisPool = cache.unwrap(JedisPool.class);
        Jedis jedis = jedisPool.getResource();
        Boolean exist = jedis.getbit(key, offset);
        return exist;
    }


    @RequestMapping("/jetcache/jedis/getrange")
    public String getrange(String key, long startOffset, long endOffset) {
        JedisPool jedisPool = cache.unwrap(JedisPool.class);
        Jedis jedis = jedisPool.getResource();
        String subValue = jedis.getrange(key, startOffset, endOffset);
        return subValue;
    }

    @RequestMapping("/jetcache/jedis/getSet")
    public String getSet(String key, String value) {
        JedisPool jedisPool = cache.unwrap(JedisPool.class);
        Jedis jedis = jedisPool.getResource();
        String oldValue = jedis.getSet(key, value);
        return oldValue;
    }

    @RequestMapping("/jetcache/jedis/incr")
    public Long incr(String key) {
        JedisPool jedisPool = cache.unwrap(JedisPool.class);
        Jedis jedis = jedisPool.getResource();
        Long newValue = jedis.incr(key);
        return newValue;
    }

    @RequestMapping("/jetcache/jedis/incrBy")
    public Long incrBy(String key, long increment) {
        JedisPool jedisPool = cache.unwrap(JedisPool.class);
        Jedis jedis = jedisPool.getResource();
        Long newValue = jedis.incrBy(key, increment);
        return newValue;
    }

    @RequestMapping("/jetcache/jedis/incrByFloat")
    public Double incrByFloat(String key, double increment) {
        JedisPool jedisPool = cache.unwrap(JedisPool.class);
        Jedis jedis = jedisPool.getResource();
        Double newValue = jedis.incrByFloat(key, increment);
        return newValue;
    }


    @RequestMapping("/jetcache/jedis/mget")
    public List<String> mget(String ...keys) {
        JedisPool jedisPool = cache.unwrap(JedisPool.class);
        Jedis jedis = jedisPool.getResource();
        List<String> values = jedis.mget(keys);
        return values;
    }

    @RequestMapping("/jetcache/jedis/mset")
    public String mset(String ...keysvalues) {
        JedisPool jedisPool = cache.unwrap(JedisPool.class);
        Jedis jedis = jedisPool.getResource();
        String flag = jedis.mset(keysvalues);
        return flag;
    }

    @RequestMapping("/jetcache/jedis/msetnx")
    public Long msetnx(String ...keysvalues) {
        JedisPool jedisPool = cache.unwrap(JedisPool.class);
        Jedis jedis = jedisPool.getResource();
        Long flag = jedis.msetnx(keysvalues);
        return flag;
    }

    @RequestMapping("/jetcache/jedis/psetex")
    public String psetex(String key, long milcse, String value) {
        JedisPool jedisPool = cache.unwrap(JedisPool.class);
        Jedis jedis = jedisPool.getResource();
        String replayCode = jedis.psetex(key, milcse, value);
        return replayCode;
    }


    @RequestMapping("/jetcache/jedis/setbit")
    public Boolean setbit(String key, long offset, String value) {
        JedisPool jedisPool = cache.unwrap(JedisPool.class);
        Jedis jedis = jedisPool.getResource();
        Boolean replayCode = jedis.setbit(key, offset, value);
        return replayCode;
    }

    @RequestMapping("/jetcache/jedis/setex")
    public String setex(String key, int second, String value) {
        JedisPool jedisPool = cache.unwrap(JedisPool.class);
        Jedis jedis = jedisPool.getResource();
        String replayCode = jedis.setex(key, second, value);
        return replayCode;
    }


    @RequestMapping("/jetcache/jedis/setrange")
    public Long setrange(String key, long offset, String value) {
        JedisPool jedisPool = cache.unwrap(JedisPool.class);
        Jedis jedis = jedisPool.getResource();
        Long replayCode = jedis.setrange(key, offset, value);
        return replayCode;
    }


    @RequestMapping("/jetcache/jedis/strlen")
    public Long strlen(String key) {
        JedisPool jedisPool = cache.unwrap(JedisPool.class);
        Jedis jedis = jedisPool.getResource();
        Long strlen = jedis.strlen(key);
        return strlen;
    }
}
