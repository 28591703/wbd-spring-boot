package com.wbd.spring.boot.entity;
/**
 * 测试一对一 嵌套查询
* <p>Title: TUser.java</p>  
* <p>Description: </p>  
* @author 朱光和 
* @date 2018年8月6日
 */
public class TUser {

	
	private Long id;
	
	private String tName;
	
	private String pwd;
	
	private TAddress address;

	public TAddress getAddress() {
		return address;
	}

	public void setAddress(TAddress address) {
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	
}
