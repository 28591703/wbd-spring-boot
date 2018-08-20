package com.wbd.spring.boot.security.basic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.boot.ibatis.mapper.SysUserMapper;
import com.wbd.spring.boot.entity.SysRole;
import com.wbd.spring.boot.entity.SysUser;

/**
 * 自定义userdetailservice
 * 将权限给spring security管理 必须实现 userdetailService接口
* <p>Title: CustomUserService.java</p>  
* <p>Description: </p>  
* @author 朱光和 
* @date 2018年8月15日
 */
@Service
public class CustomUserService implements UserDetailsService{
	
	@Autowired
	private SysUserMapper  sum;
    
	/* 重写  loaduserbyusername方法
	 * <p>Title: loadUserByUsername</p>  
	 * <p>Description: </p>  
	 * @param username
	 * @return
	 * @throws UsernameNotFoundException  
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)  
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		SysUser su=sum.selectUserByName(username);
		if(su==null) {
			throw new UsernameNotFoundException("用户名不存在");
		}
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		
		//将用户的权限添加到authorities
	
			
			for (SysRole r:su.getRoleList()) {
				
				authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
				System.out.println("角色名称："+r.getRoleName());
			
			
		}
		return new User(su.getUserName(),su.getUserPassword(),authorities);
	}

}
