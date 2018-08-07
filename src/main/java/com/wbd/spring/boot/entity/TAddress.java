package com.wbd.spring.boot.entity;

/**
 * 测试一对一 嵌套查询
* <p>Title: TAddress.java</p>  
* <p>Description: </p>  
* @author 朱光和 
* @date 2018年8月6日
 */
public class TAddress {
	
	private Long userId;
	
	private String address;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	

}
