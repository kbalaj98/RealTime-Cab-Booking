package com.mycab.common.redis.service;


import java.time.Duration;

public interface RedisService
{
    //set object
    public void addObject(String key,Object obj);
    public void addObject(String key, Object obj, Duration duration);
    public void addPoolSpecificObject(String db,String key,Object obj);
    public void addPoolSpecificObject(String db,String key,Object obj, Duration duration);

    //get object
    public Object getObject(String key);
    public Object getPoolSpecificObject(String db,String key);


    //delete object
    public void  deleteObject(String key);
    public void  deleteObject(String db,String key);
}
