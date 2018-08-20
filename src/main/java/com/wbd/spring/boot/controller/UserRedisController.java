package com.wbd.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wbd.spring.boot.entity.User;
import com.wbd.spring.boot.service.UserService;
import com.wbd.spring.boot.utils.JSONResult;
/**
 * redis 操作
* <p>Title: UserRedisController.java</p>  
* <p>Description: </p>  
* @author 朱光和 
* @date 2018年8月13日
 */
@RestController
@RequestMapping("/redis")
public class UserRedisController {
	
	@Autowired
	private UserService us ;
	
	
	
	@RequestMapping(value="/getUser/{userId}")  
    public User getUser(@PathVariable Integer userId) {  
        
        return us.findById(userId);  
    }  
      
	/**
	 * https://localhost:9999/wbd/saveUser
	 * {"roleName":"zgh","enable":"1"}
	 * <p>Title: saveUser</p>  
	 * <p>Description: </p>  
	 * @param user
	 * @return
	 */
    @RequestMapping(value="/saveUser",method = RequestMethod.POST,produces="application/json;charset=utf-8")  
    public String saveUser(@RequestBody User user) {  
    	 us.createUser(user);  
    
    	return "result:"+user.getId();
    }  
   
    
    
    @RequestMapping(value="/updateUser",method = RequestMethod.POST,produces="application/json;charset=utf-8")  
    public String update(@RequestBody User user) {  
    	 int result = us.updateUser(user);  
    	return "result:"+result;
    } 
    
    
    @RequestMapping(value="/deleteUser/{userId}")  
    public String delete(@PathVariable Long userId) {  
    	 int result = us.delete(userId);  
    	return "result:"+result;
    } 
    
    
  
    @RequestMapping(value="/getAllUser" ,method = RequestMethod.GET,produces="application/json;charset=UTF-8") 
    public  String  getAllUser() {  
    	
        return JSONResult.fillResultString(1,"succcess",us.findAll());  
    }  
    
    
  

}
