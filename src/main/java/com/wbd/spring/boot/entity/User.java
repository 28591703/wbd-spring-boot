package com.wbd.spring.boot.entity;

public class User {
	
	/**
	 * javabean中的类型尽量不要声明为基本数据类型， 
	 * 如果声明，可能会导致错误， 因为数据库中的int类型其实对应的是Integer引用数据类型
	 */
	private Long id;
	
	private String roleName;
	
	private Integer enable;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}
	
	

}
