package com.demoipm.dto.general;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DatatableParamRequestDTO implements Serializable {

    private Integer start;

    private Integer length;

    private SearchParamDTO search;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public SearchParamDTO getSearch() {
        return search;
    }

    public void setSearch(SearchParamDTO search) {
        this.search = search;
    }
}
