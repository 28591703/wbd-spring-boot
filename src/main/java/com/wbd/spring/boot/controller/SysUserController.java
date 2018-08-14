package com.wbd.spring.boot.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.ibatis.mapper.SysPrivilegeMapper;
import com.spring.boot.ibatis.mapper.SysRoleMapper;
import com.spring.boot.ibatis.mapper.SysUserMapper;
import com.wbd.spring.boot.entity.SysPrivilege;
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
	
	@Autowired
	private SysRoleMapper srm;
	
	@Autowired
	private SysPrivilegeMapper spm;
	
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
		u.setId(120L);
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
	
	/**
	 *  2.update中用if
	 * <p>Title: updateSysUserByIdSelective</p>  
	 * <p>Description: </p>  
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/updateSysUserByIdSelective",method=RequestMethod.POST) 
	Integer updateSysUserByIdSelective(@RequestBody SysUser user){
	
		return sum.updateSysUserByIdSelective(user);
	}
	
	
	/**
	 * 3.insert中用if
	 * <p>Title: insertSysUserByIf</p>  
	 * <p>Description: </p>  
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/insertSysUserByIf",method=RequestMethod.POST) 
	Integer insertSysUserByIf(@RequestBody SysUser user){
	
		return sum.insertSysUserByIf(user);
	}
	
	
	
	/**
	 * 4.choose when otherwise 来替代if else  else if  else
	 * <p>Title: selectSysUserByIfAndElse</p>  
	 * <p>Description: </p>  
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/selectSysUserByIfAndElse",method=RequestMethod.POST) 
	SysUser selectSysUserByIfAndElse(@RequestBody SysUser user){
	
		return sum.selectSysUserByIfAndElse(user);
	}
	
	
	/**5,where 标签  可以防止sql注入， 避免where 1=1 的问题 
	 * <p>Title: selectSysUserByWhere</p>  
	 * <p>Description: </p>  
	 * @param user
	 * @return
	 */
	
	@RequestMapping(value="/selectSysUserByWhere",method=RequestMethod.POST) 
	List<SysUser> selectSysUserByWhere(@RequestBody SysUser user){
		return sum.selectSysUserByWhere(user);
	}
	
	
	/**
	 * 6.set 标签和update标签搭配使用
	 * <p>Title: updateSysUserBySet</p>  
	 * <p>Description: </p>  
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/updateSysUserBySet",method=RequestMethod.POST) 
	Integer updateSysUserBySet(@RequestBody SysUser user){
		return sum.updateSysUserBySet(user);
	}
	
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
	@RequestMapping(value="/selectSysUserByForeach") 
	List<SysUser> selectSysUserByForeach(){
		List<Long> idList   = new ArrayList<Long>();
		idList.add(1l);
		idList.add(119l);
		idList.add(1001l);
		return sum.selectSysUserByForeach(idList);
	}
	
	
	/**
	 * foreache的使用，可以防止sql注入
	 * 比如：where id in(1,2,3,)  
	 * 
	 * 2.参数为数组
	 * <p>Title: selectSysUserByForeachArray</p>  
	 * <p>Description: </p>  
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/selectSysUserByForeachArray")
	List<SysUser> selectSysUserByForeachArray(){
		Long[] idArray =new Long[] {1004l,1005l,1006l};
		return sum.selectSysUserByForeachArray(idArray);
	}
	
	@RequestMapping(value="/selectSysUserByForeachArrayAndName")
	List<SysUser> selectSysUserByForeachArrayAndName(){
		Long[] idArray =new Long[] {1001l,1003l,1004l,1005l,1006l};
		return sum.selectSysUserByForeachArrayAndName("李",idArray);
	}
	
	
	
	@RequestMapping(value="/updateSysUserByForeachMap")
	Integer updateSysUserByForeachMap(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("user_name", "朱明泽");  //key必须是数据库中的字段
		map.put("user_info", "hello kity");
		map.put("id", "1003");
		return sum.updateSysUserByForeachMap(map,1003);
	}
	
	
	@RequestMapping(value="/insertBath")
	Integer insertBath(){
		List<SysUser> userList = new ArrayList<SysUser>();
		
		for(int i=0;i<5;i++)
		{
			SysUser u = new SysUser();
			u.setUserName("test"+i);
			u.setUserPassword("123456");
			u.setUserEmail("285917033@qq.com");
			u.setUserInfo("hello world"+i);
			userList.add(u);
		}
		
		return sum.insertBath(userList);
	}
	
	
	/***********************一对多查询******************/
	
	/**
	 * 1 。 一对多的查询  查询所有  角色信息
	 * <p>Title: selectAllUserAndRoles</p>  
	 * <p>Description: </p>  
	 * @return
	 */
	@RequestMapping(value="/selectAllUserAndRoles")
	List<SysUser> selectAllUserAndRoles(){
		return sum.selectAllUserAndRoles();
	}
	
	
	/**
	 * 2. 一对多的查询  根据id查询 所有  角色信息
	 * <p>Title: selectAllUserAndRoles</p>  
	 * <p>Description: </p>  
	 * @return
	 */
	@RequestMapping(value="/selectAllUserAndRolesByUserId/{id}",method = RequestMethod.GET)
	List<SysUser> selectAllUserAndRolesByUserId(@PathVariable Long id){
		return sum.selectAllUserAndRolesByUserId(id);
	}
	
	
	
	
	/**
	 * 3.一对多的查询  ,查询用户所有的角色， 角色所对应的所有权限
	 * <p>Title: selectAllUserAndRoles</p>  
	 * <p>Description: </p>  
	 * @return
	 */
	@RequestMapping(value="/selectAllUserAndRolesAndPrivilege",method = RequestMethod.GET)
	List<SysUser> selectAllUserAndRolesAndPrivilege(){
		return sum.selectAllUserAndRolesAndPrivilege();
	}
	
	
	
	/**
	 * 4.一对多的查询  ,查询 角色所对应的所有权限
	 * <p>Title: selectAllUserAndRoles</p>  
	 * <p>Description: </p>  
	 * @return
	 */
	@RequestMapping(value="/selectAllRoleAndPrivileges",method = RequestMethod.GET)
	List<SysRole> selectAllRoleAndPrivileges(){
		return srm.selectAllRoleAndPrivileges();
	}
	
	
	
	/**
	 * 5.一对多的查询  ,查询所有权限
	 * <p>Title: selectAllUserAndRoles</p>  
	 * <p>Description: </p>  
	 * @return
	 */
	@RequestMapping(value="/selectAllPrivilege",method = RequestMethod.GET)
	List<SysPrivilege> selectAllPrivilege(){
		return spm.selectAllPrivilege();
	}
	
	
	
	/**
	 * 6.根据角色id查询对应的权限列表
	 * <p>Title: selectAllPrivilegeByRoleId</p>  
	 * <p>Description: </p>  
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value="/selectAllPrivilegeByRoleId/{roleId}",method = RequestMethod.GET)
	List<SysPrivilege> selectAllPrivilegeByRoleId(@PathVariable Long roleId){
		return spm.selectAllPrivilegeByRoleId(roleId);
	}
	
	
	/**
	 * 7.根据userid查询角色信息
	 * <p>Title: selectAllRoleByUserId</p>  
	 * <p>Description: </p>  
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/selectAllRoleByUserId/{userId}",method = RequestMethod.GET)
	List<SysRole> selectAllRoleByUserId(@PathVariable Long userId){
		return srm.selectAllRoleByUserId(userId);
	}
	
	
	
	/**
	 * 8 利用用户id查询对应的角色和权限信息
	 * <p>Title: selectUserRoleListMapSelect</p>  
	 * <p>Description: </p>  
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/selectUserRoleListMapSelect/{id}",method = RequestMethod.GET)
	List<SysUser> selectUserRoleListMapSelect(@PathVariable  Long id){
		return sum.selectUserRoleListMapSelect(id);
	}
	
	
	
/********************存储过程的调用****************/
	
	
	//1.根据用户id查询出对应的信息 ，返回值为空
	//采用javabean，存储过程中的入参和出参必须在javabean属性中存在
	
	
	@RequestMapping(value="/selectUserByIdCallable/{userId}",method = RequestMethod.GET)
	SysUser selectUserByIdCallable(@PathVariable  Long userId){
		SysUser u = new SysUser ();
		u.setId(userId);
		sum.selectUserByIdCallable(u);
		System.out.println(u.getUserName());
		System.out.println(u.getUserEmail());
		System.out.println(u.getUserPassword());
		
		return u;
	}
	
	
	
	
	/**
	 * 2.分页查询，参数采用map
	 * 返回值为集合 ,即resultMap，
	     与上面的select的区别是， 第一个select没有返回值， 而这个定义了返回值
	   参数采用map,参数名称可以不需要和存储过程的参数名一致
	 * <p>Title: selectUserByIdCallableReturnList</p>  
	 * <p>Description: </p>  
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/selectUserByIdCallableReturnList",method = RequestMethod.GET)
	List<SysUser> selectUserByIdCallableReturnList(){
		Map<String, Object> params = new HashMap<String,Object>();
		//设置存储过程需要传入的参数
		params.put("userName", "a");
		params.put("offset", "0");
		params.put("limit", "5");
		
		List<SysUser> list = sum.selectUserByIdCallableReturnList(params);
		//获取存过程返回的参数，通过map.get("key")查询
		Long total = (Long) params.get("total");
		System.out.println("总记录数====="+total);
		return list;
	}
	
	
	/**
	 * 3.插入 多个参数采用 @Param的形式
	 * <p>Title: insertUserAndRole</p>  
	 * <p>Description: </p>
	 */
	@RequestMapping(value="/insertUserAndRole",method = RequestMethod.GET)

	void insertUserAndRole(){
		
		SysUser u = new SysUser ();
		u.setUserName("proc");
		u.setUserEmail("proc@qq.com");
		u.setHeadImg(new byte[] {1,3,4,});
		u.setUserInfo("proc test");
		u.setUserPassword("123456");
		String roleIds = "1,2";
		
		sum.insertUserAndRole(u, roleIds);
		
		System.out.println("id=="+u.getId());
		System.out.println("创建时间=="+u.getCreateTime());
		
		int result = sum.deleteUserAndRoleById(u.getId());
		
		System.out.println("删除结果:"+result);
	}
}
