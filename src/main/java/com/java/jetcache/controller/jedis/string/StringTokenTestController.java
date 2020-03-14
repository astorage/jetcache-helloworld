package com.java.jetcache.controller.jedis.string;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CreateCache;
import com.alicp.jetcache.anno.SerialPolicy;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Boris
 * @date 2020/3/12 18:04
 */
@RestController
public class StringTokenTestController {

    @CreateCache(serialPolicy = SerialPolicy.JAVA)
    private Cache<String, Object> cache;


    @RequestMapping("/test_cache_redis/string/getall")
    public Set<String> queryUserIdList (@RequestBody List<String> tokenList) {
        JedisPool jedisPool = cache.unwrap(JedisPool.class);
        //JedisPool jedisPool = (JedisPool) redisCache.unwrap();
        Jedis jedis = jedisPool.getResource();
        jedis.select(7);
        Set<String> userSet = new HashSet<>();
        Set<String> tokenSet = new HashSet<>();
        for (String token : tokenList) {
            String userId = jedis.get(token);
            userId = handleUserCode1(userId);
            if (userId != null && !userSet.contains(userId)) {
                userSet.add(userId);
                tokenSet.add(token);
            }
        }
        System.out.println(userSet.size());
        return tokenSet;
    }

    private static final String CUSTOMER = "CUSTOMER";
    private static final String SERVICER = "SERVICER";
    private static final String POPER = "POPER";
    private String handleUserCode(String userId) {
        if (userId != null) {
            String[] customers = userId.split(CUSTOMER);
            if (customers.length < 2) {
                String[] servicers = userId.split(SERVICER);
                if (servicers.length < 2) {
                    String[] popers = userId.split(POPER);
                    return POPER + "-" + popers[1];
                }
                return SERVICER + "-" + servicers[1];
            }
            return CUSTOMER + "-" + customers[1];

        }
        return null;
    }


    private String handleUserCode1(String userId) {
        if (userId != null) {
            String[] customers = userId.split(CUSTOMER);
            if (customers.length < 2) {
               return null;
            }
            return customers[1];

        }
        return null;
    }


}
