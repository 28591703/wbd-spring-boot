package com.spring.boot.ibatis.mapper;

import java.util.List;

import com.wbd.spring.boot.entity.SysPrivilege;

/**
 * 权限
* <p>Title: SysRoleMapper.java</p>  
* <p>Description: </p>  
* @author 朱光和 
* @date 2018年8月7日
 */
public interface SysPrivilegeMapper {
	
	
	/***************************CRUD*****************************/
	
	
	List<SysPrivilege> selectAllPrivilege();
	
	
	/**
	 * 根据角色id查询对应的权限列表
	 * <p>Title: selectAllPrivilegeByRoleId</p>  
	 * <p>Description: </p>  
	 * @param roleId
	 * @return
	 */
	List<SysPrivilege> selectAllPrivilegeByRoleId(Long roleId);
	
}
