package com.phuongnt.service;

import java.util.List;

import com.phuongnt.dto.UserAppDto;

public interface UserAppService {

	void create(UserAppDto userAppDto);
	
	UserAppDto readById(int id) throws Exception;
	
	List<UserAppDto> readAll() throws Exception ;
	
	void update(UserAppDto userAppDto);
	
	void deleteById(int id);
}
