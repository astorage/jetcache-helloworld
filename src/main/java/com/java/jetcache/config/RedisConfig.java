package com.java.jetcache.config;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CreateCache;
import com.alicp.jetcache.anno.SerialPolicy;
import com.alicp.jetcache.redis.RedisCache;
import com.alicp.jetcache.redis.RedisCacheConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author Boris
 * @date 2020/3/11 19:42
 */
@Configuration
public class RedisConfig {

    @CreateCache(serialPolicy = SerialPolicy.JAVA)
    private Cache<String, Object> cache;

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

    @Bean
    public Jedis jedis() {
        JedisPool jedisPool = cache.unwrap(JedisPool.class);
        Jedis jedis = jedisPool.getResource();
        return jedis;
    }



    @Bean
    public RedisTemplate<String, Object> rpRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        if (!(redisConnectionFactory instanceof LettuceConnectionFactory)) {
            throw new RuntimeException(
                    "unsuport redis connection factory! " + redisConnectionFactory);
        }
        LettuceConnectionFactory lettuceConnectionFactory = (LettuceConnectionFactory) redisConnectionFactory;
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(
                lettuceConnectionFactory.getHostName(), lettuceConnectionFactory.getPort());
        //redisStandaloneConfiguration.setDatabase(0);
        redisStandaloneConfiguration.setPassword(lettuceConnectionFactory.getPassword());
        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(
                redisStandaloneConfiguration, lettuceConnectionFactory.getClientConfiguration());
        connectionFactory.afterPropertiesSet(); //这句一定不能少

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        StringRedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setConnectionFactory(connectionFactory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        return redisTemplate;
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
