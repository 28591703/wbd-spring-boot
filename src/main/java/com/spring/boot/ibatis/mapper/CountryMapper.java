package com.spring.boot.ibatis.mapper;

import java.util.List;

import com.wbd.spring.boot.entity.Country;

public interface CountryMapper {
	
	public List<Country> selectAll();

}
