package com.wbd.spring.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.ibatis.mapper.CountryMapper;
import com.wbd.spring.boot.entity.Country;

@RestController
public class MyBatisXmlUserController {
	
	@Autowired
	private CountryMapper cm ;
	
	@RequestMapping(value="/getAllUser") 
    public  List<Country> getAll() {  
        return cm.selectAll();  
    }  
      
	

}
