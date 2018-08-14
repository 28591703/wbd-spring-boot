package com.spring.boot.ibatis.mapper;

import java.util.List;
import java.util.Map;

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
	 * <p>Title: updateSysUserByIdSelective</p>  
	 * <p>Description: </p>  
	 * @param user
	 * @return
	 */
	Integer updateSysUserByIdSelective(SysUser user);
	
	
	
	/**3.insert中用if
	 * <p>Title: selectAllSysUserByCondition</p>  
	 * <p>Description: </p>  
	 * @param user
	 * @return
	 */
	Integer insertSysUserByIf(SysUser user);
	
	
	/**4.choose when otherwise 来替换if else  else if  else,
	 * choose必须包含一个when，有0个或者1个otherwise
	 * <p>Title: selectAllSysUserByCondition</p>  
	 * <p>Description: </p>  
	 * @param user
	 * @return
	 */
	SysUser selectSysUserByIfAndElse(SysUser user);
	
	
	/**5,where 标签  可以防止sql注入， 避免where 1=1 的问题 
	 * choose必须包含一个when，有0个或者1个otherwise
	 * <p>Title: selectAllSysUserByCondition</p>  
	 * <p>Description: </p>  
	 * @param user
	 * @return
	 */
	List<SysUser> selectSysUserByWhere(SysUser user);
	
	
	/**  
	 * 6.set 标签和update标签搭配使用
	 * <p>Title: insertSysUserByIf</p>  
	 * <p>Description: </p>  
	 * @param user
	 * @return  
	 */ 
	Integer updateSysUserBySet(SysUser user);
	
	/**
	 * 7.foreache的使用，可以防止sql注入
	 * 比如：where id in(1,2,3,)  
	 * 
	 * 1.参数为集合
	 * <p>Title: selectSysUserByForeach</p>  
	 * <p>Description: </p>  
	 * @param user
	 * @return
	 */
	List<SysUser> selectSysUserByForeach(List<Long> idList);
	
	
	/**
	 * foreache的使用，可以防止sql注入
	 * 比如：where id in(1,2,3,)  
	 * 
	 * 2.参数为数组
	 * <p>Title: selectSysUserByForeach</p>  
	 * <p>Description: </p>  
	 * @param user
	 * @return
	 */
	List<SysUser> selectSysUserByForeachArray(Long[] idArray);
	
	/**
	 * 3.多个参数， 比如，有一个参数为数组的，推荐方法定义中采用@Param,此时ibatis中的collection必须指定为@Param所指定的名字
      select * from sys_user u  where u.user_name like '%李%' and  id in(1001,1003,1004,1005,1006)
   
	 * <p>Title: selectSysUserByForeachArrayAndName</p>  
	 * <p>Description: </p>  
	 * @param idArray
	 * @return
	 */
	List<SysUser> selectSysUserByForeachArrayAndName(@Param("userName") String userName, @Param("idArray") Long[] idArray);
	
	
	/**
	 * 4.参数为map
   
	 * <p>Title: selectSysUserByForeachMap</p>  
	 * <p>Description: </p>  
	 * @param idArray
	 * @return
	 */
	Integer updateSysUserByForeachMap(@Param("map")Map<String,Object> map,@Param("id") Integer id);
	
	
	Integer insertBath (List<SysUser> userList);
	
	
	
	/***********************一对多查询******************/
	
	
	/**
	 * 1 。 一对多的查询  查询所有  角色信息
	 * <p>Title: selectAllUserAndRoles</p>  
	 * <p>Description: </p>  
	 * @return
	 */
	List<SysUser> selectAllUserAndRoles();
	
	
	
	/**
	 * 2. 一对多的查询  根据id查询 所有  角色信息
	 * <p>Title: selectAllUserAndRoles</p>  
	 * <p>Description: </p>  
	 * @return
	 */
	List<SysUser> selectAllUserAndRolesByUserId(Long id);
	
	
	/**
	 * 3.一对多的查询  ,查询用户所有的角色， 角色所对应的所以权限
	 * <p>Title: selectAllUserAndRolesAndPrivilege</p>  
	 * <p>Description: </p>  
	 * @return
	 */
	List<SysUser> selectAllUserAndRolesAndPrivilege();
	
	/**
	 * 4利用用户id查询对应的角色和权限信息
	 * <p>Title: selectUserRoleListMapSelect</p>  
	 * <p>Description: </p>  
	 * @param id
	 * @return
	 */
	List<SysUser> selectUserRoleListMapSelect(Long id);
	
	
	
	/********************存储过程的调用****************/
	
	
	//1.根据用户id查询出对应的信息 返回值为空，采用javabean，存储过程中的入参和出参必须在javabean属性中存在
	
	void selectUserByIdCallable(SysUser user);
	
	
	/**
	 * 2.分页查询，参数采用map,返回值为集合 ,即resultMap，
	     与上面的select的区别是， 第一个select没有返回值， 而这个定义了返回值
	   参数采用map,参数名称可以不需要和存储过程的参数名一致
	 * <p>Title: selectUserByIdCallableReturnList</p>  
	 * <p>Description: </p>  
	 * @param params
	 * @return
	 */
	List<SysUser> selectUserByIdCallableReturnList(Map<String, Object> params);
	
	/**
	 * 3.插入 多个参数采用 @Param的形式
	 * <p>Title: insertUserAndRole</p>  
	 * <p>Description: </p>  
	 * @param user
	 * @param roleIds
	 * @return
	 */
	int insertUserAndRole(@Param("user") SysUser user,@Param("roleIds") String roleIds);
	
	/**
	 * 4.用户删除
	 * <p>Title: deleteUserAndRoleById</p>  
	 * <p>Description: </p>  
	 * @param userId
	 * @return
	 */
	int deleteUserAndRoleById(Long userId);
}
