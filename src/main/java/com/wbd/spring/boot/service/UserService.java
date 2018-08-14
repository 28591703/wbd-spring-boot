package com.wbd.spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.ibatis.mapper.UserMapper;
import com.wbd.spring.boot.config.UserRedis;
import com.wbd.spring.boot.entity.User;

/**
 * 业务逻辑层，对redis的使用
* <p>Title: UserService.java</p>  
* <p>Description: </p>  
* @author 朱光和 
* @date 2018年8月13日
 */
@Service
public class UserService {
	
	    @Autowired
	    private UserMapper um;
	    @Autowired
	    private UserRedis userRedis;
	    private static final String keyHead="mysql:get:user:";

       /**
        * 1.先去缓存中查询，如果缓存中没有， 
        * 2.去数据库中查询， 
        * 3.把查询到的结果 再添加到缓存中	    
        * <p>Title: findById</p>  
        * <p>Description: </p>  
        * @param userId
        * @return
        */
	    public User findById(Integer userId) {
	    	User u = userRedis.get(keyHead+userId);
	    	if(u==null)
	    	{
	    		u=um.getById(userId);
	    		
	    		if(u!=null) {
	    			userRedis.add(keyHead+userId, 2l, u);
	    		}
	    	}
	    		return u;
	    	
	    }
	    
	    
	    /**
	     * 创建用户
	     * 1.先调用插入
	     * 2.在把用户信息插入到缓存
	     * <p>Title: createUser</p>  
	     * <p>Description: </p>  
	     * @param user
	     */
	    public void createUser(User user)
	    {
	    	um.save(user);
	    	if(user!=null) {
	    		userRedis.add(keyHead+user.getId(), 2l, user);
	    	}
	    }
	    

	    
	    
	    /**
	     * 更新用户
	     * 1.先删除掉缓存的数据,
	     * 2.然后把最新的添加到缓存中
	     * 3.再来更新数据库
	     * <p>Title: updateUser</p>  
	     * <p>Description: </p>  
	     * @param user
	     */
	    public int updateUser(User user) {
	    	
	    	if(user!=null) {
	    	userRedis.delete(keyHead+user.getId());
	    	userRedis.add(keyHead+user.getId(), 2l, user);
	    	}
	    	
	    	return um.updateUser(user);
	    	
	    }
	    
	    
	    
	    public int delete(Long id)
	    {
	    	userRedis.delete(keyHead+id);
	    	return um.deleteUser(id);
	    }
	    
	    
	    public List<User> findAll(){
	    	String key = "keyHeadall";
	    	List<User> users = userRedis.getList(key);
	    	
	    	if(users==null) {
	    		users = um.getAll();
	    		
	    		if(users!=null)
	    		{
	    			userRedis.addAll(key, 2l, users);
	    		}
	    	}
	    	
	    	return users;
	    }
}
