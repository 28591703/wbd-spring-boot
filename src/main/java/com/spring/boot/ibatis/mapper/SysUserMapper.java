package com.spring.boot.ibatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wbd.spring.boot.entity.SysRole;
import com.wbd.spring.boot.entity.SysUser;

public interface SysUserMapper {
	
	
	/***************************CRUD*****************************/
	
	
	/**
	 * 根据用户id查询
	 * <p>Title: selectById</p>  
	 * <p>Description: </p>  
	 * @param id
	 * @return
	 */
	SysUser selectSysUserById(Long id);
	
	
	/**  
	 * 查询所有
	 * <p>Title: selectAll</p>  
	 * <p>Description: </p>  
	 * @param id
	 * @return  
	 */ 
	List<SysUser> selectAllSysUser();
	
	
	/**
	 * 根据用户id查询 该用户的所有角色信息
	 * <p>Title: selectAllSysRoleByUserId</p>  
	 * <p>Description: </p>  
	 * @param id
	 * @return
	 */
	List<SysRole> selectAllSysRoleByUserId(Long id);
	
	
	/**
	 * 插入用户
	 * <p>Title: insertSysUser</p>  
	 * <p>Description: </p>  
	 * @param user
	 * @return
	 */
	Integer insertSysUser(SysUser user);
	
	
	
	/***************************插入返回主键*****************************/
	
	/**
	 * 插入用户 返回数据库的id ,使用jdbc的方式返回主键自增值
	 * <p>Title: insertSysUserReturnAutoId</p>  
	 * <p>Description: </p>  
	 * @param user
	 * @return
	 */
	Integer insertSysUserReturnAutoId(SysUser user);
	
	/**
	 * 采用selectkey返回主键的值， 类如oracle没有主键自增， 只能通过序列，
	 * 这种情况用selectkey形式
	 * 
	 * <p>Title: insertSysUserReturnSelectKey</p>  
	 * <p>Description: </p>  
	 * @param user
	 * @return
	 */
	Integer insertSysUserReturnSelectKey(SysUser user);
	
	/**
	 * 用户更新
	 * <p>Title: updateSysUser</p>  
	 * <p>Description: </p>  
	 * @param user
	 * @return
	 */
	Integer updateSysUser (SysUser user);
	
	/**
	 * 
	 * <p>Title: selectSysUserById</p>  
	 * <p>Description: </p>  
	 * @param id
	 * @return
	 */
	Integer deleteSysUserById(Long id);
	
	
	
	
	/***************************多个接口参数*****************************/
	/**
	 * 当多个参数不是一个javabean时
	 * 用户id为1 ，角色 createby=1的用户,此时userid与create_by不在同一个javabean，而且为了
	 * 2个字段去建立一个javabean没有必须，我们可以采用@Param
	 * 
	 * SQL= select r.id,r.role_name roleName,r.create_by createBy,r.create_time createTime,
		u.user_name ,u.user_email  from sys_user u 
          INNER JOIN sys_user_role ur on u.id=ur.user_id
          INNER JOIN sys_role r on ur.role_id=r.id
           where  u.id=1 and r.create_by=1
	 * 
	 */
	

	/**
	 * 1.多个接口参数 采用@Param
	 * 根据用户id和 createby=1查询 该用户的所有角色信息
	 * <p>Title: selectAllSysRoleByUserId</p>  
	 * <p>Description: </p>  
	 * @param id
	 * @return
	 */
	List<SysRole> selectAllSysRoleByUserIdAndCreate(@Param("userId") Long userId,@Param("createBy") Integer createBy);
	
	
	
	/**
	 * 
	 * 当有多个javabean做参数时,可以替换上面的方式
	 * xml中只能用#{user.id}和#{role.createBy}
	 * 
	 */
	List<SysRole> selectAllSysRoleByBeans(@Param("user") SysUser user,@Param("role") SysRole role);

	
	/***************************动态sql*****************************/
	
	/**1.where 中用if
	 * 根据条件查询用户信息
	 * <p>Title: selectAllSysUserByCondition</p>  
	 * <p>Description: </p>  
	 * @param user
	 * @return
	 */
	List<SysUser> selectAllSysUserByCondition(SysUser user);
	
	
	/**2.update中用if
	 * 根据条件查询用户信息
	 * <p>Title: selectAllSysUserByCondition</p>  
	 * <p>Description: </p>  
	 * @param user
	 * @return
	 */
	Integer updateSysUserByIdSelective(SysUser user);
	
}