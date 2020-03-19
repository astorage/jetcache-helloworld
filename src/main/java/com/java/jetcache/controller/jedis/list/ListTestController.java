package com.java.jetcache.controller.jedis.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author Boris
 * @date 2020/3/16 15:03
 */
@RestController
public class ListTestController {

    @Autowired
    private Jedis jedis;

    @RequestMapping("/test/list/lpush")
    public Long lpush(String key, @RequestBody String[] values) {
        Long listNewLenght = jedis.lpush(key, values);
        return listNewLenght;
    }

    @RequestMapping("/test/list/lpop")
    public String lpop(String key) {
        String headElement = jedis.lpop(key);
        return headElement;
    }
    @RequestMapping("/test/list/lpushx")
    public Long lpushx(String key, @RequestBody String[] values) {
        Long listNewLenght = jedis.lpushx(key, values);
        return listNewLenght;
    }

    @RequestMapping("/test/list/lindex")
    public String lindex(String key, long index) {
        String element = jedis.lindex(key, index);
        return element;
    }
    @RequestMapping("/test/list/linsert/before")
    public Long linsertBefore(String key,String pivot, String value) {
        Long listNewLength = jedis.linsert(key,  Client.LIST_POSITION.BEFORE, pivot, value);
        return listNewLength;
    }
    @RequestMapping("/test/list/linsert/after")
    public Long linsertAfter(String key, String pivot, String value) {
        Long listNewLength = jedis.linsert(key, Client.LIST_POSITION.AFTER, pivot, value);
        return listNewLength;
    }
    @RequestMapping("/test/list/llen")
    public Long llen(String key) {
        Long listLength = jedis.llen(key);
        return listLength;
    }

    /**
     * 返回列表是闭区间
     * @param key 键
     * @param start 下标起始值
     * @param stop  下标结束值  负数是相反的方向
     * @return 列表闭区间
     */
    @RequestMapping("/test/list/lrange")
    public List<String> lrange(String key, Long start, Long stop) {
        List<String> elementList = jedis.lrange(key, start, stop);
        return elementList;
    }

    /**
     * 移除列表元素
     * @param key
     * @param count 移除数量（
     *              count > 0 : 从表头开始向表尾搜索，移除与 value 相等的元素，数量为 count
     *              count < 0 : 从表尾开始向表头搜索，移除与 value 相等的元素，数量为 count 的绝对值
     *              count = 0 : 移除表中所有与 value 相等的值
     *              ）
     * @param value 需要移除的值
     * @return 返回移除的数量
     */
    @RequestMapping("/test/list/lrem")
    public Long lpop(String key, Long count, String value) {
        Long removed = jedis.lrem(key, count, value);
        return removed;
    }
    @RequestMapping("/test/list/lset")
    public String lset(String key, Long index, String value) {
        String statusCodeReply = jedis.lset(key, index, value);
        return statusCodeReply;
    }

    /**
     * 保留指定范围的元素
     * @param key
     * @param start 开始下标
     * @param stop  结束下标
     * @return
     */
    @RequestMapping("/test/list/ltrim")
    public String ltrim(String key, Long start, Long stop) {
        String statusCodeReply = jedis.ltrim(key, start, stop);
        return statusCodeReply;
    }


    @RequestMapping("/test/list/rpop")
    public String rpop(String key) {
        String tailElement = jedis.rpop(key);
        return tailElement;
    }



    @RequestMapping("/test/list/rpoplpush")
    public String rpoplpush(String sourceKey, String destinationKey) {
        String element = jedis.rpoplpush(sourceKey, destinationKey);
        return element;
    }


    @RequestMapping("/test/list/rpush")
    public Long rpush(String key, String[] vlaues) {
        Long listNewLength = jedis.rpush(key, vlaues);
        return listNewLength;
    }

    @RequestMapping("/test/list/rpushx")
    public Long rpushx(String key, String[] vlaues) {
        Long listNewLength = jedis.rpushx(key, vlaues);
        return listNewLength;
    }

    /**
     *
     * @param sourceKey
     * @param destinationKey
     * @param timeout  单位秒
     * @return
     */
    @RequestMapping("/test/list/brpoplpush")
    public String brpoplpush(String sourceKey, String destinationKey, Integer timeout) {
        String returnStr = jedis.brpoplpush(sourceKey, destinationKey, timeout);
        return returnStr;
    }
    @RequestMapping("/test/list/brpop")
    public List<String> brpop(String key) {
        List<String> returnMsgList = jedis.brpop(key);
        return returnMsgList;
    }
    @RequestMapping("/test/list/blpop")
    public List<String> blpop(String key) {
        List<String> returnMsgList = jedis.blpop(key);
        return returnMsgList;
    }
}
