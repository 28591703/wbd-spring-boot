package com.wbd.spring.boot.beans;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 类型安全的配置， 基于properties文件
* <p>Title: WbdDTO.java</p>  
* <p>Description: </p>  
* @author 朱光和 
* @date 2018年7月28日
 */

@Component //通过@Component  表示为组件，被spring boot管理
@PropertySource(value="classpath:/wbd.properties")//基于属性文件，指定位置，表示该类与属性文件关联一起
@ConfigurationProperties(prefix="wbd") //属性的前缀
public class WbdDTO {
	
	private String user;
	
	private String password;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	

}
