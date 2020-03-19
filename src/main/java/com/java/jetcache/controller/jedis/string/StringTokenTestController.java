package com.java.jetcache.controller.jedis.string;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CreateCache;
import com.alicp.jetcache.anno.SerialPolicy;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.*;
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
            userId = handleUserCode(userId);
            if (userId != null && !userSet.contains(userId)) {
                userSet.add(userId);
                tokenSet.add(token.substring(16));
            }
        }
        System.out.println(userSet.size());
        return tokenSet;
    }


    @RequestMapping("/test_cache_redis/string/getall/fromFile")
    public Set<String> queryUserIdListByFile () throws Exception{
        String inputFilePath = "E:\\jmeter-data\\tokens.txt";
        BufferedReader bufferedReader = getreader(inputFilePath);
        String outFilePath = "E:\\jmeter-data\\test-all-tokens.csv";
        BufferedWriter bufferedWriter = getwriter(outFilePath);
        JedisPool jedisPool = cache.unwrap(JedisPool.class);
        Jedis jedis = jedisPool.getResource();
        jedis.select(7);
        Set<String> userSet = new HashSet<>();
        Set<String> tokenSet = new HashSet<>();
        String token;
        while ((token = bufferedReader.readLine()) != null) {
            String userId = jedis.get(token);
            userId = handleUserCode(userId);
            if (userId != null && !userSet.contains(userId)) {
                userSet.add(userId);
                tokenSet.add(token.substring(16));
                bufferedWriter.write(token.substring(16));
                bufferedWriter.newLine();
            }
        }
        bufferedWriter.flush();
        bufferedWriter.close();
        bufferedReader.close();
        System.out.println(userSet.size());
        return tokenSet;
    }

    private BufferedReader getreader(String filePath) {
        try {
            File file = new File(filePath);
            FileInputStream inputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            InputStreamReader reader = new InputStreamReader(bufferedInputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            return bufferedReader;
        }catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }


    private BufferedWriter getwriter(String filePath)  {
        try {
            File fileout = new File(filePath);
            FileOutputStream fileOutputStream = new FileOutputStream(fileout);
            OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            return bufferedWriter;
        }catch (Exception e) {
            System.out.println(e);
        }
       return null;
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
