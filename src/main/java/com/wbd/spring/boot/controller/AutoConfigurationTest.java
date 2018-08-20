package com.wbd.spring.boot.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 直接转向view 的控制器,没有任何业务逻辑
 * 1.前提需要继承 WebMvcConfigurerAdapater
 * 2.实现对应的方法 addViewController
* <p>Title: AutoConfigurationTest.java</p>  
* <p>Description: </p>  
* @author 朱光和 
* @date 2018年7月30日
 */
@Configuration
public class AutoConfigurationTest extends WebMvcConfigurerAdapter {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/test").setViewName("test");
		registry.addViewController("/login").setViewName("login");
	}

}
