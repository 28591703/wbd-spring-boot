package com.wbd.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.ibatis.mapper.TAddressMapper;
import com.spring.boot.ibatis.mapper.TUserMapper;
import com.wbd.spring.boot.entity.TAddress;
import com.wbd.spring.boot.entity.TUser;
/**
 * 一对一的查询
* <p>Title: QueryUserController.java</p>  
* <p>Description: </p>  
* @author 朱光和 
* @date 2018年8月6日
 */
@RestController
public class QueryUserController {
	
	@Autowired
	private TUserMapper tum ;
	
	@Autowired
	private TAddressMapper tam ;
	
	
	/**
	 * 第一种方式：一对一的查询
	 * <p>Title: selectTUserById</p>  
	 * <p>Description: </p>  
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/selectTUserById/{id}",method = RequestMethod.GET) 
    public  TUser selectTUserById(@PathVariable Long id) {  
        return tum.selectTUserById(id);  
    }  
	
	
	
	/**
	 * 第二种方式：一对一的查询
	 * <p>Title: selectTUserByIdSecond</p>  
	 * <p>Description: </p>  
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/selectTUserByIdSecond/{id}",method = RequestMethod.GET) 
    public  TUser selectTUserByIdSecond(@PathVariable Long id) {  
        return tum.selectTUserByIdSecond(id);  
    }  
	
	
	
	/**
	 * 第三种方式：一对一的查询  association 采用javaType
	 * <p>Title: selectTUserByIdThree</p>  
	 * <p>Description: </p>  
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/selectTUserByIdThree/{id}",method = RequestMethod.GET) 
    public  TUser selectTUserByIdThree(@PathVariable Long id) {  
        return tum.selectTUserByIdThree(id);  
    }  
	
	
	
	/**
	 * 第四种， association标签的方式 ,标签属性,association 采用resultMap形式
	 * <p>Title: selectTUserByIdFoux</p>  
	 * <p>Description: </p>  
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/selectTUserByIdFoux/{id}",method = RequestMethod.GET) 
    public  TUser selectTUserByIdFoux(@PathVariable Long id) {  
        return tum.selectTUserByIdFoux(id);  
    }  
	
	
	
	/**
	 * 查询address
	 * <p>Title: selectTUserByIdFoux</p>  
	 * <p>Description: </p>  
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/selectAddressById/{id}",method = RequestMethod.GET) 
    public  TAddress selectAddressById(@PathVariable Long id) {  
        return tam.selectAddressById(id);  
    }  
	
	
	
	/**
	 * 第五种， association标签嵌套查询
	 * <p>Title: selectUserAndAddressById</p>  
	 * <p>Description: </p>  
	 * @param id
	 * @return
	 */
	
	@RequestMapping(value="/selectUserAndAddressById/{id}",method = RequestMethod.GET) 
    public  TUser selectUserAndAddressById(@PathVariable Long id) {  
        return tum.selectUserAndAddressById(id);  
    }  
}
