package com.mycab.booking.config;

import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class RedisConfig
{
    private static HashMap<String, RedisTemplate<String,Object>> templateHashMap = new HashMap<>();

    static
    {
        initRedis();
    }

    private static void initRedis()
    {
       List<String> dbs =  List.of("0","1","2","3");

       dbs.stream().forEach((db)->{
           templateHashMap.put("db_"+db,getRedisTemplateObj(db));
       });
    }

    private static RedisTemplate<String,Object> getRedisTemplateObj(String db)
    {
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory();
        lettuceConnectionFactory.setDatabase(Integer.parseInt(db));
        lettuceConnectionFactory.afterPropertiesSet();

        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

    public RedisTemplate<String,Object> getRedisTemplate()
    {
        return  getRedisTemplate("db_0");
    }

    public RedisTemplate<String,Object> getRedisTemplate(String db)
    {
        if(templateHashMap.containsKey(db))
        {
            return templateHashMap.get(db);
        }
        return templateHashMap.get("db_0");
    }


}
