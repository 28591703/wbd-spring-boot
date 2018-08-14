package com.wbd.spring.boot.config;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

/**
 * 自定义mybatis缓存
 * 使用Redis来做Mybatis的二级缓存
 * 实现Mybatis的Cache 5个接口
* <p>Title: MybatisRedisCache.java</p>  
* <p>Description: </p>  
* @author 朱光和 
* @date 2018年8月13日
 */
public class MybatisRedisCache implements Cache{
	private static final Logger logger = LoggerFactory.getLogger(MybatisRedisCache.class);
	 
    // 读写锁
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
 
     // redisTemplate bean的名称 在RedisConfig类中已经通过@Bean声明
    // 如果需要调用通过spring容器获得redisTemplate对象来操作，
    //首先必须通过Spring Aware（容器感知）来获取到ApplicationContext，
    private RedisTemplate<String, Object> redisTemplate = SpringContextHolder.getBean("redisTemplate");
 
    private String id;
 
      /**
       * 必须提供一个为id的构造器
      * <p>Title: </p>  
      * <p>Description: </p>  
      * @param id
       */
    public MybatisRedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        logger.info("Redis Cache id " + id);
        this.id = id;
    }
 
    @Override
    public String getId() {
        return this.id;
    }
 
    @Override
    public void putObject(Object key, Object value) {
        if (value != null) {
            // 向Redis中添加数据，有效时间是2个小时
            redisTemplate.opsForValue().set(key.toString(), value, 2, TimeUnit.HOURS);
        }
    }
 
    @Override
    public Object getObject(Object key) {
        try {
            if (key != null) {
                Object obj = redisTemplate.opsForValue().get(key.toString());
                return obj;
            }
        } catch (Exception e) {
            logger.error("redis ");
        }
        return null;
    }
 
    @Override
    public Object removeObject(Object key) {
        try {
            if (key != null) {
                redisTemplate.delete(key.toString());
            }
        } catch (Exception e) {
        }
        return null;
    }
 
    @Override
    public void clear() {
        logger.debug("清空缓存");
        try {
        	//redisTemplate.keys 模糊匹配， 得到set集合， 
            Set<String> keys = redisTemplate.keys("*:" + this.id + "*");
            if (!CollectionUtils.isEmpty(keys)) {
                redisTemplate.delete(keys);
            }
        } catch (Exception e) {
        }
    }
 
    @Override
    public int getSize() {
        Long size = (Long) redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.dbSize();
            }
        });
        return size.intValue();
    }
 
    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }

}
