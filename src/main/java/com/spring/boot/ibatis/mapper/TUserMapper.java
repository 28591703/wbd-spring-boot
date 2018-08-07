package com.spring.boot.ibatis.mapper;

import com.wbd.spring.boot.entity.TUser;

public interface TUserMapper {
	
	/**
	 * 第一种方式：一对一的查询
	 * <p>Title: selectTUserById</p>  
	 * <p>Description: </p>  
	 * @param id
	 * @return
	 */
	public TUser selectTUserById(Long id);

	
	
	/**
	 * 第二种方式：一对一的查询
	 * <p>Title: selectTUserByIdSecond</p>  
	 * <p>Description: </p>  
	 * @param id
	 * @return
	 */
	public TUser selectTUserByIdSecond(Long id);
	
	
	
	/**
	 * 第三种方式：一对一的查询  association 采用javaType
	 * <p>Title: selectTUserByIdThree</p>  
	 * <p>Description: </p>  
	 * @param id
	 * @return
	 */
	public TUser selectTUserByIdThree(Long id);
	
	
	/**
	 * 第四种， association标签的方式 ,标签属性,association 采用resultMap形式
	 * <p>Title: selectTUserByIdFoux</p>  
	 * <p>Description: </p>  
	 * @param id
	 * @return
	 */
	public TUser selectTUserByIdFoux(Long id);
	
	

	/**
	 * 第五种， association标签嵌套查询
	 * <p>Title: selectUserAndAddressById</p>  
	 * <p>Description: </p>  
	 * @param id
	 * @return
	 */
	public TUser selectUserAndAddressById(Long id);
	
}
