package com.spring.boot.ibatis.mapper;

import java.util.List;

import com.wbd.spring.boot.entity.SysRole;

/**
 * 角色
* <p>Title: SysRoleMapper.java</p>  
* <p>Description: </p>  
* @author 朱光和 
* @date 2018年8月7日
 */
public interface SysRoleMapper {
	
	
	/***************************CRUD*****************************/
	
	
	/**
	 * 1.查询角色所对应的权限
	 * <p>Title: selectAllRoleAndPrivileges</p>  
	 * <p>Description: </p>  
	 * @return
	 */
	List<SysRole> selectAllRoleAndPrivileges();
	
	
	/**2.根据userid查询对应的角色
	 * 
	 * <p>Title: selectAllRoleByUserId</p>  
	 * <p>Description: </p>  
	 * @param userId
	 * @return
	 */
	List<SysRole> selectAllRoleByUserId(Long userId);
	
	
}
