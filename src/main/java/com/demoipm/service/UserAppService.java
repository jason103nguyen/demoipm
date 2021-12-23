package com.demoipm.service;

import com.demoipm.dto.UserAppDto;
import com.demoipm.dto.general.ResponseDto;
import com.demoipm.dto.usermanage.UserCreateRequestDto;
import com.demoipm.dto.usermanage.UserListPageResponseDto;

import java.util.List;

public interface UserAppService {

	void create(UserAppDto userAppDto);
	
	UserAppDto readById(int id) throws Exception;
	
	List<UserAppDto> readAll() throws Exception ;
	
	void update(UserAppDto userAppDto);
	
	void deleteById(int id);

	UserListPageResponseDto readByCondition(String searchWord, int pageNo, int entriesNo);

	void createNewUser(UserCreateRequestDto requestDto, ResponseDto responseDto);
}
