package com.demoipm.dto.recruitmentmanage;

import com.demoipm.dto.general.ResponseDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecruitmentListPageResponseDto extends ResponseDto implements Serializable {

    private int currentPage;
    private int entriesNo;
    private int totalPage;
    private long totalEntries;
    private List<RecruitmentResponseDto> recruitmentList;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getEntriesNo() {
        return entriesNo;
    }

    public void setEntriesNo(int entriesNo) {
        this.entriesNo = entriesNo;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotalEntries() {
        return totalEntries;
    }

    public void setTotalEntries(long totalEntries) {
        this.totalEntries = totalEntries;
    }

    public List<RecruitmentResponseDto> getRecruitmentList() {
        return recruitmentList;
    }

    public void setRecruitmentList(List<RecruitmentResponseDto> recruitmentList) {
        this.recruitmentList = recruitmentList;
    }
}
