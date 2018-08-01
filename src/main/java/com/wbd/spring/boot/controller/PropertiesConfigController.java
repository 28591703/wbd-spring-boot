package com.wbd.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wbd.spring.boot.beans.WbdDTO;
@RestController
public class PropertiesConfigController {
	@Autowired
	private WbdDTO  dto;
	
	
	@RequestMapping("/prop")
	String prop() {
		return "prop name  is:"+dto.getUser()+"and prop  pwd is :"+dto.getPassword();
	}
	
	
	

	
}
