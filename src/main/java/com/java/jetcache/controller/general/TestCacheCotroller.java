package com.java.jetcache.controller.general;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.alicp.jetcache.redis.RedisCache;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class TestCacheCotroller {

    @CreateCache(name = "test",cacheType = CacheType.BOTH, localLimit = 1000, expire = 24, timeUnit = TimeUnit.HOURS)
    private Cache<String, String> testCache;




    @PostMapping("/test_cache")
    public String testCache(@RequestBody HashMap<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            testCache.put(entry.getKey(), entry.getValue());
        }

        return "yes";
    }

    @PostMapping("/test_cache/getBase")
    public Map<String, String> getCache(@RequestBody List<String> list) {
        Map<String, String> map = new HashMap<>();
        for (String key : list) {
            String value = testCache.get(key);
            map.put(key, value);
        }

        return map;
    }




}
