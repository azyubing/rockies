package com.rockies.webservice.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class TestRedisConnection {

    public static void main(String args[]){  
//        Jedis test = new Jedis("127.0.0.1",6379);        
//        test.set("hw","hello world");  
//        String hello = test.get("hw");  
//        System.out.println(hello);  
        
        String redisAddress = "127.0.0.1";
        int redisPort = 6379;
        int redisTimeout = 2000;

        JedisPool pool = new JedisPool(new JedisPoolConfig(), redisAddress, redisPort, redisTimeout);

        Jedis jedis = pool.getResource();
        jedis.set("test123", "lulu");
        jedis.set("test456", "mingming");

        pool.returnResource(jedis);
        
    }  
}
