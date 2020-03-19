package com.java.jetcache.controller.springredis;

import com.java.jetcache.model.Address;
import com.java.jetcache.model.Student;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author Boris
 * @date 2020/3/19 9:03
 */
@RestController
public class TemplateTest {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

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
        Set keys = redisTemplate.keys("test*");
        System.out.println(keys);

        //redisTemplate.getConnectionFactory().getClusterConnection().select(0);
        //connectionFactory.setDatabase(1);//选择1号数据库
        Object value = redisTemplate.opsForValue().get(key);
        System.out.println(value.toString());
        return value.toString();
    }

}
