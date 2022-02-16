package com.demoipm.service;

import com.demoipm.dto.UserAppDto;
import com.demoipm.dto.general.DatatableParamRequestDTO;
import com.demoipm.dto.general.DatatableResponseDTO;
import com.demoipm.dto.general.ResponseDto;
import com.demoipm.dto.usermanage.UserCreateRequestDto;
import com.demoipm.dto.usermanage.UserUpdateRequestDto;

import java.util.List;

public interface UserAppService {

	void create(UserAppDto userAppDto);
	
	UserAppDto readById(int id) throws Exception;
	
	List<UserAppDto> readAll() throws Exception ;
	
	void update(UserAppDto userAppDto);
	
	void deleteById(int id);

	/**
	 * Read user list by search word, page number and entries number
	 * @param request
	 * @return
	 */
	DatatableResponseDTO readByCondition(DatatableParamRequestDTO request);

	/**
	 * Create new user from create request
	 * @param requestDto
	 * @param responseDto
	 */
	void createNewUser(UserCreateRequestDto requestDto, ResponseDto responseDto);

	/**
	 * Read user by username
	 * @param username
	 * @param responseDto
	 * @return
	 */
	UserUpdateRequestDto readUserByUsername(String username, ResponseDto responseDto);

	/**
	 * Update existed user from request input
	 * @param requestDto
	 * @param responseDto
	 */
	void updateExistedUser(UserUpdateRequestDto requestDto, ResponseDto responseDto);

	/**
	 * Delete user by username
	 * @param username
	 */
	void deleteUserByUsername(String username);
}
