package com.java.jetcache.controller.springredis;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.java.jetcache.model.Address;
import com.java.jetcache.model.Student;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Boris
 * @date 2020/3/19 9:03
 */
@RestController
public class TemplateTest {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private RedisTemplate<String, Object> rpRedisTemplate;

    @CreateCache(name = "test",cacheType = CacheType.BOTH, localLimit = 1000, expire = 24, timeUnit = TimeUnit.HOURS)
    private Cache<String, String> testCache;
    @Resource
    private Jedis jedis;

    @RequestMapping("/test/set/add")
    public void testSetAdd(String key, @RequestBody Student student) {
        redisTemplate.opsForSet().add(key, student);
    }

    @RequestMapping("/test/set/smembers")
    public void testSetSmembers() {

    }


    @RequestMapping("/test/setString/get")
    public String testSetSmembers(String key) {
        LettuceConnectionFactory factory = (LettuceConnectionFactory) redisTemplate.getConnectionFactory();
        System.out.println(factory.getDatabase());
        Set keys = redisTemplate.keys("nne_po_*");
        System.out.println(keys);

        //redisTemplate.getConnectionFactory().getClusterConnection().select(0);
        //connectionFactory.setDatabase(1);//选择1号数据库
        Object value = redisTemplate.opsForValue().get(key);
        System.out.println(value.toString());
        return value.toString();
    }


    @RequestMapping("/test/hash/get")
    public String hashGet(String key, String field) {
        jedis.select(7);
        String value = jedis.hget(key,field);
        System.out.println(value);
        return value;
    }

    @RequestMapping("/test/hash/springredis")
    public String hashGet1(String key, String field) {
        LettuceConnectionFactory factory = (LettuceConnectionFactory) rpRedisTemplate.getConnectionFactory();
        System.out.println(factory.getDatabase());
//        factory.setDatabase(7);
//        factory.afterPropertiesSet();
//        System.out.println(factory.getDatabase());
//        redisTemplate.setConnectionFactory(factory);

        Object value = rpRedisTemplate.opsForHash().get(key, field);
        System.out.println(value.toString());
        return value.toString();
    }
}
