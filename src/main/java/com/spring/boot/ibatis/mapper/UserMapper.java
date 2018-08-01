package com.spring.boot.ibatis.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.wbd.spring.boot.entity.User;


public interface UserMapper 
{
	/*******************mybatis注解的方式**********/
	/**
	 * 查询
	 * <p>Title: getById</p>  
	 * <p>Description: </p>  
	 * @param userId
	 * @return
	 */
	
	@Select("select id,role_name,enabled from sys_role where id=#{userId}")
	@Results({
		@Result(property = "id",  column = "id"), //id true表示表中的主键 ，唯一性
		@Result(property = "roleName", column = "role_name"),
		@Result(property = "enable", column = "enabled")
	})
	public User getById(Integer userId);
	
	/**
	 * 返回自增主键
	 * 返回自增长ID,是返回到user对象， 而具体的insert方法执行之后还是返回的影响条数
	 * @Options(useGeneratedKeys=true,keyProperty="id")
	 * <p>Title: save</p>  
	 * <p>Description: </p>  
	 * @param user
	 * @return
	 */
	@Options(useGeneratedKeys=true,keyProperty="id",keyColumn="id")
	@Insert("insert into sys_role (role_name,enabled) values(#{roleName},#{enable})")
	public Integer save(User user);
	
	
	
	/**
	 * 返回非自增主键
	 * mysql主键通过last_insert_id()返回
	 * before=false表示mysql插入之后返回的主键
	 * before=true表示oralce插入之前获得的序列
	 * oracle通过序列 返回
	 * 返回自增长ID,是返回到user对象， 而具体的insert方法执行之后还是返回的影响条数
	 * @Options(useGeneratedKeys=true,keyProperty="id")
	 * <p>Title: save</p>  
	 * <p>Description: </p>  
	 * @param user
	 * @return
	 */
	@Insert("insert into sys_role (role_name,enabled) values(#{roleName},#{enable})")
	@SelectKey(statement="select last_insert_id() as id",keyProperty="id",keyColumn="id",resultType=Long.class,before=false)
	public Integer unSave(User user);
	
	@Update("update sys_role set role_name=#{roleName},enabled=${enable} where id=#{id}")
	public Integer updateUser(User user);
	
	@Delete("delete from sys_role  where id=#{id}")
	public Integer deleteUser(Long Id);

}
