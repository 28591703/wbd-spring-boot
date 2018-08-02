package com.wbd.spring.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.ibatis.mapper.SysUserMapper;
import com.spring.boot.ibatis.mapper.UserMapper;
import com.wbd.spring.boot.entity.SysUser;
import com.wbd.spring.boot.entity.User;
/**
 * mybatis 注解方式
* <p>Title: MyBatisUserController.java</p>  
* <p>Description: </p>  
* @author 朱光和 
* @date 2018年8月1日
 */
@RestController
public class MyBatisUserController {
	
	@Autowired
	private UserMapper um ;
	
	@Autowired
	private SysUserMapper sum ;
	
	@RequestMapping(value="/getUser/{userId}")  
    public User getUser(@PathVariable Integer userId) {  
        
        return um.getById(userId);  
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
    	 um.save(user);  
    
    	return "result:"+user.getId();
    }  
    /**
     * 返回自增长ID,是返回到user对象， 而具体的insert方法执行之后还是返回的影响条数
     * <p>Title: unSaveUser</p>  
     * <p>Description: </p>  
     * @param user
     * @return
     */
    @RequestMapping(value="/unSaveUser",method = RequestMethod.POST,produces="application/json;charset=utf-8")  
    public String unSaveUser(@RequestBody User user) {  
    	 um.unSave(user);  
    	return "result:"+user.getId();
    } 
    
    
    @RequestMapping(value="/updateUser",method = RequestMethod.POST,produces="application/json;charset=utf-8")  
    public String update(@RequestBody User user) {  
    	 int result = um.updateUser(user);  
    	return "result:"+result;
    } 
    
    
    @RequestMapping(value="/deleteUser/{userId}")  
    public String delete(@PathVariable Long userId) {  
    	 int result = um.deleteUser(userId);  
    	return "result:"+result;
    } 
    
    
  
    
    @RequestMapping(value="/getAllSysUser" ,method = RequestMethod.GET) 
    public  List<SysUser> getAllSysUser() {  
        return sum.selectAllSysUser();  
    }  

}
