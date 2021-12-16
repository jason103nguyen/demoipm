package com.phuongnt.service;

import java.util.List;

import com.phuongnt.dto.RecruitmentDto;

public interface RecruitmentService {

	void create(RecruitmentDto recruitmentDto);
	
	RecruitmentDto readById(int id) throws Exception;
	
	List<RecruitmentDto> readAll() throws Exception ;
	
	void update(RecruitmentDto recruitmentDto);
	
	void deleteById(int id);
}
