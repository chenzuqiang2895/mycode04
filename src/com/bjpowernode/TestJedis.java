package com.bjpowernode;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestJedis {

    public static void main(String[] args) {

//        创建jedis对象
//        Jedis jedis = new Jedis("192.168.75.128",6379);

//        使用连接池来操作redis数据库
        JedisPool jedisPool = RedisUtils.open("192.168.75.128", 6379);
//        获取连接对象
        Jedis jedis = jedisPool.getResource();

//        删除数据
        jedis.flushDB();

        jedis.set("str1","aaa");
        jedis.set("str2","bbb");
        jedis.set("str3","ccc");

//        获取数据
        String str1 = jedis.get("str1");
        System.out.println(str1);

        System.out.println("==============");

//        存数据
        Map<String,String> map=new HashMap<String, String>();
        map.put("id","1001");
        map.put("name","陈冠希");
        map.put("age","35");
        jedis.hmset("student", map);

//        获取对象
        List<String> student = jedis.hmget("student", "id", "name", "age");
        for (String s : student) {
            System.out.println(s);
        }

//        关闭连接
        jedisPool.close();
    }
}
