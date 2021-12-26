package com.demoipm.dto;

import com.demoipm.entities.Candidate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.time.LocalTime;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InterviewRequest {

    private Integer id;

    private Candidate candidate;

    @NotBlank
    protected Date timeInterview;

    @NotBlank
    protected Date date;

    @NotBlank
    private String location;

    private String nameInterview;

}
