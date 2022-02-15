package com.demoipm.dto.potentialcandidate;

import java.util.List;

import com.demoipm.dto.CandidateDto;

public class PotentialCandidatePageDto {

	String sortBy;

	String keySearch;

	String direction;

	int pageNo;

	int totalPage;

	int currentPage;

	List<CandidateDto> listPotentialCandidate;

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
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

	public List<CandidateDto> getListPotentialCandidate() {
		return listPotentialCandidate;
	}

	public void setListPotentialCandidate(List<CandidateDto> listPotentialCandidate) {
		this.listPotentialCandidate = listPotentialCandidate;
	}

}
