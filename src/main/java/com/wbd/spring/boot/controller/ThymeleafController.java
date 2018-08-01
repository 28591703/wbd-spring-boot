package com.wbd.spring.boot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wbd.spring.boot.beans.Person;
/**
 *  thymeleaf 自动注解配置，默认配置如下
 *   spring.thymeleaf.prefix=classpath:/templates/
      spring.thymeleaf.suffix=.html
* <p>Title: ThymeleafController.java</p>  
* <p>Description: </p>  
* @author 朱光和 
* @date 2018年7月29日
 */
@Controller
public class ThymeleafController {
	
	@RequestMapping("/")
	public String index(Model model) {
		List<Person> lp =new ArrayList<Person>();
		
		
		Person sp1 = new Person("张三",100);
		Person sp2 = new Person("李四",200);
		Person sp3 = new Person("王五",300);
		
		lp.add(sp1);
		lp.add(sp2);
		lp.add(sp3);
		
		
		Person sp = new Person();
		sp.setName("zs");
		sp.setAge(10);
		model.addAttribute("singePerson", sp);
		model.addAttribute("person", lp);
		
		return "index";
		
	}
	

}
