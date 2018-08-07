package com.spring.boot.ibatis.mapper;

import com.wbd.spring.boot.entity.TAddress;

public interface TAddressMapper {
	
	TAddress selectAddressById(Long id);
	
}
