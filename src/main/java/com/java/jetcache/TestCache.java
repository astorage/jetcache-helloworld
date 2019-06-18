package com.java.jetcache;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class TestCache {

    @CreateCache(name = "test",cacheType = CacheType.BOTH, localLimit = 1000, expire = 24, timeUnit = TimeUnit.HOURS)
    private Cache<String, String> testCache;

    @PostMapping("/test_cache")
    public String testCache(@RequestBody HashMap<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            testCache.put(entry.getKey(), entry.getValue());
        }

        return "yes";
    }

}
