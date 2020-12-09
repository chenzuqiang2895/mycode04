package com.bjpowernode;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

//对事务的操作
public class TestJedis02 {

    public static void main(String[] args) {

        JedisPool jedisPool = RedisUtils.open("192.168.75.128", 6379);
//        获取连接
        Jedis jedis = jedisPool.getResource();

//        删除
        jedis.flushDB();
//        开启事务
        Transaction multi = jedis.multi();
        multi.set("str1","aaa");
        multi.set("str2","bbb");
        multi.set("str3","ccc1");

//        提交事务
        multi.exec();

//        关闭连接
        jedisPool.close();

    }
}
