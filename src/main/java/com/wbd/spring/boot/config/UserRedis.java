package com.wbd.spring.boot.config;
/**
 * 第二种 实现redis方式，不是实现Mybatis的Cache方式
* <p>Title: UserRedis.java</p>  
* <p>Description: </p>  
* @author 朱光和 
* @date 2018年8月13日
 */

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.alibaba.druid.util.StringUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wbd.spring.boot.entity.User;

@Repository
public class UserRedis {

	//自动注入redisTemplate
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	
	/**
	 * 添加操作时，把数据存入缓存中
	 * 
	 * <p>Title: add</p>  
	 * <p>Description: </p>  
	 * @param key 缓存中 的key
	 * @param time 存储多久， 
	 * @param user 缓存中 的value，转换为json存储
	 */
	public void add(String key,Long time,User user) {
		Gson gson=new Gson();
		//将对象转换为json
		redisTemplate.opsForValue().set(key, gson.toJson(user), time, TimeUnit.HOURS);
		
	}
	
	
	public void addAll(String key,Long time,List<User> list) {
		Gson gson=new Gson();
		//将对象转换为json
		redisTemplate.opsForValue().set(key, gson.toJson(list), time, TimeUnit.HOURS);
		
	}
	
	/**
	 * 查询操作
	 * <p>Title: get</p>  
	 * <p>Description: </p>  
	 * @param key
	 * @return
	 */
	public User get(String key)
	{
		Gson gson=new Gson();
		User user=null;
		String userJson = redisTemplate.opsForValue().get(key);
		if(!StringUtils.isEmpty(userJson)) {
			//将json转换为对象
			user = gson.fromJson(userJson, User.class);
		}
		
		return user;
	}
	
	
	/**
	 * 获取所有
	 * <p>Title: getList</p>  
	 * <p>Description: </p>  
	 * @param key
	 * @return
	 */
	public List<User> getList(String key){
		Gson gson=new Gson();
		 List<User> users = null;
		 String listJson = redisTemplate.opsForValue().get(key);
		 if(!StringUtils.isEmpty(listJson)) {
				//将json转换为list对象
				users = gson.fromJson(listJson, new TypeToken<List<User>>(){}.getType());
			}
		 return users;
	}
	
	
	/**
	 * 删除
	 * <p>Title: delete</p>  
	 * <p>Description: </p>  
	 * @param key
	 */
	public void delete(String key) {
		
		redisTemplate.opsForValue().getOperations().delete(key);
	}
	
}
