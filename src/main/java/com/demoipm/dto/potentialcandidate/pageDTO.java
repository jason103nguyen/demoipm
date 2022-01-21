package com.demoipm.dto.potentialcandidate;

import java.util.List;

import com.demoipm.dto.CandidateDto;

public class pageDTO {

	String field;

	String keySearch;

	int pageNo;

	int totalPage;

	int currentPage;

	List<CandidateDto> list;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getKeySearch() {
		return keySearch;
	}

	public void setKeySearch(String keySearch) {
		this.keySearch = keySearch;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<CandidateDto> getList() {
		return list;
	}

	public void setList(List<CandidateDto> list) {
		this.list = list;
	}

}
