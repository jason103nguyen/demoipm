package com.phuongnt.service;

import java.util.List;

import com.phuongnt.dto.UserRoleDto;

public interface UserRoleService {

	void create(UserRoleDto userRoleDto);
	
	UserRoleDto readById(int id) throws Exception;
	
	List<UserRoleDto> readAll() throws Exception ;
	
	void update(UserRoleDto userRoleDto);
	
	void deleteById(int id);
	
}
