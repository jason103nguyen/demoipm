package com.demoipm.dto.usermanage;

import com.demoipm.dto.general.ResponseDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserListPageResponseDto extends ResponseDto implements Serializable {

    private int currentPage;
    private int entriesNo;
    private int totalPage;
    private long totalEntries;
    private List<UserResponseDto> userList;

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

    public List<UserResponseDto> getUserList() {
        return userList;
    }

    public void setUserList(List<UserResponseDto> userList) {
        this.userList = userList;
    }
}
