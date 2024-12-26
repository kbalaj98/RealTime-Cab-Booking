package com.mycab.common.redis.service;

import com.mycab.common.redis.config.RedisConfig;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@AllArgsConstructor
@Service
public class RedisServiceImpl implements RedisService
{
    @Autowired
    RedisConfig redisConfig;


    @Override
    public void addObject(String key, Object obj)
    {
        redisConfig.getRedisTemplate().opsForValue().set(key,obj);
    }

    @Override
    public void addObject(String key, Object obj, Duration duration) {

        RedisTemplate<String,Object> redisTemplate = redisConfig.getRedisTemplate();
        redisTemplate.opsForValue().set(key,obj);
        redisTemplate.expire(key,duration);
    }

    @Override
    public Object getObject(String key)
    {
        return redisConfig.getRedisTemplate().opsForValue().get(key);
    }

    @Override
    public void deleteObject(String key)
    {
        redisConfig.getRedisTemplate().delete(key);
    }

    @Override
    public void addPoolSpecificObject(String db, String key, Object obj)
    {
        redisConfig.getRedisTemplate(db).opsForValue().set(key,obj);
    }

    @Override
    public void addPoolSpecificObject(String db, String key, Object obj, Duration duration) {

        RedisTemplate<String,Object> redisTemplate = redisConfig.getRedisTemplate(db);
        redisTemplate.opsForValue().set(key,obj);
        redisTemplate.expire(key,duration);
    }

    @Override
    public Object getPoolSpecificObject(String db, String key)
    {
        return redisConfig.getRedisTemplate(db).opsForValue().get(key);
    }

    @Override
    public void deleteObject(String db, String key)
    {
        redisConfig.getRedisTemplate(db).delete(key);
    }
}
