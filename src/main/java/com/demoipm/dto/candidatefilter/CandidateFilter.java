package com.demoipm.dto.candidatefilter;

import java.util.ArrayList;
import java.util.List;

public class CandidateFilter {

	private String content;
	
	private Integer minAge;
	
	private Integer maxAge;
	
	private List<Integer> listSkills = new ArrayList<Integer>();
	
	public CandidateFilter() {}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getMinAge() {
		return minAge;
	}

	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}

	public Integer getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(Integer maxAge) {
		this.maxAge = maxAge;
	}

	public List<Integer> getListSkills() {
		return listSkills;
	}

	public void setListSkills(List<Integer> listSkills) {
		this.listSkills = listSkills;
	}

}
