package com.wbd.spring.boot.security.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 配置哪些资源可以访问必须继承websecurityconfigureradapter
* <p>Title: WebSecurityConfig.java</p>  
* <p>Description: </p>  
* @author 朱光和 
* @date 2018年8月15日
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	/**
	 * 注册bean
	 * <p>Title: customUserService</p>  
	 * <p>Description: </p>  
	 * @return
	 */
	@Bean
	UserDetailsService customUserService() {
		
		return new CustomUserService();
	}
	
	/**
	 * 验证bean
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserService());
	}
	
	
//定义http资源	
@Override
protected void configure(HttpSecurity http) throws Exception {
	http.authorizeRequests()
	.anyRequest().authenticated()//任何请求认证即登录后才能访问
	.and()
	.formLogin()
	.loginPage("/login")
	.failureUrl("/login?error")
	.permitAll() //登录页面任何用户都可以访问
	.and()
	.logout().permitAll();//注销功能任何用户都可以访问
}

}
