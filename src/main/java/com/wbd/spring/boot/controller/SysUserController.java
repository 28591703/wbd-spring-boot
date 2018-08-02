package com.wbd.spring.boot.controller;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.ibatis.mapper.SysUserMapper;
import com.wbd.spring.boot.entity.SysRole;
import com.wbd.spring.boot.entity.SysUser;
/**
 * mybatis xml方式
* <p>Title: SysUserController.java</p>  
* <p>Description: </p>  
* @author 朱光和 
* @date 2018年8月1日
 */
@RestController
public class SysUserController {
	
	@Autowired
	private SysUserMapper sum ;
	
	@RequestMapping(value="/getSysUser" ,method = RequestMethod.GET) 
    public  List<SysUser> getAllSysUser() {  
        return sum.selectAllSysUser();  
    }  

	
	@RequestMapping(value="/getSysUserById/{id}",method = RequestMethod.GET) 
    public  SysUser getSysUserById(@PathVariable Long id) {  
        return sum.selectSysUserById(id);  
    }  
	
	@RequestMapping(value="/selectAllSysRoleByUserId/{id}",method = RequestMethod.GET) 
    public  List<SysRole> selectAllSysRoleByUserId(@PathVariable Long id) {  
        return sum.selectAllSysRoleByUserId(id);  
    }  
	
	@RequestMapping(value="/insertSysUser") 
    public Integer insertSysUser() {  
		SysUser u  = new SysUser();
		u.setId(119L);
		u.setUserName("朱光和");;
		u.setUserEmail("285917033@qq.com");
		u.setUserPassword("123456");
		u.setCreateTime(new Date());
		u.setHeadImg(new byte[] {1,2,30});
		u.setUserInfo("今天晚上加班");
        return sum.insertSysUser(u);  
    }  
	
	
	@RequestMapping(value="/insertSysUserReturnAutoId") 
    public String insertSysUserReturnAutoId() {  
		SysUser u  = new SysUser();
		u.setUserName("张三");;
		u.setUserEmail("285917033@qq.com");
		u.setUserPassword("123456");
		u.setCreateTime(new Date());
		u.setHeadImg(new byte[] {1,2,30});
		u.setUserInfo("今天晚上加班");
		sum.insertSysUserReturnAutoId(u);
        return "insert 返回主键 id:"+u.getId();  
    }  
	
	@RequestMapping(value="/insertSysUserReturnSelectKey") 
    public String insertSysUserReturnSelectKey() {  
		SysUser u  = new SysUser();
		u.setUserName("李四");;
		u.setUserEmail("285917033@qq.com");
		u.setUserPassword("123456");
		u.setCreateTime(new Date());
		u.setHeadImg(new byte[] {1,2,30});
		u.setUserInfo("今天晚上加班");
		sum.insertSysUserReturnAutoId(u);
        return "insert 返回主键 id:"+u.getId();  
    }  
	
	
	@RequestMapping(value="/updateSysUser") 
    public Integer updateSysUser() {  
		SysUser u  = new SysUser();
		u.setUserName("王八");;
		u.setUserEmail("aaa@qq.com");
		u.setId(1001l);
		return sum.updateSysUser(u);
    }  
	
	@RequestMapping(value="/deleteSysUser") 
	public Integer deleteSysUserById(Long id) {
		return sum.deleteSysUserById(1002l);
		
	}
	
	@RequestMapping(value="/selectAllSysRoleByUserIdAndCreate") 
	public List<SysRole> selectAllSysRoleByUserIdAndCreate(Long userId,Integer createBy){
		return sum.selectAllSysRoleByUserIdAndCreate(1l,1);
	}
	
	@RequestMapping(value="/selectAllSysRoleByBeans") 
	public List<SysRole> selectAllSysRoleByBeans(){
		SysUser user = new SysUser();
		user.setId(1L);
	     SysRole role = new SysRole();
	     role.setCreateBy("1");
		return sum.selectAllSysRoleByBeans(user,role);
	}
	
	
/***************************动态sql*****************************/
	
	/**
	 * 1.根据条件查询用户信息
	 * <p>Title: selectAllSysUserByCondition</p>  
	 * <p>Description: </p>  
	 * @param user
	 * @return
	 */
	
	@RequestMapping(value="/selectAllSysUserByCondition",method=RequestMethod.POST) 
	List<SysUser> selectAllSysUserByCondition(@RequestBody SysUser user){
	
		return sum.selectAllSysUserByCondition(user);
	}
	
	@RequestMapping(value="/updateSysUserByIdSelective",method=RequestMethod.POST) 
	Integer updateSysUserByIdSelective(@RequestBody SysUser user){
	
		return sum.updateSysUserByIdSelective(user);
	}
	
}
