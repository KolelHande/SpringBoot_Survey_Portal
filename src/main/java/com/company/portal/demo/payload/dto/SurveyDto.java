package com.company.portal.demo.payload.dto;

import com.company.portal.demo.entity.Question;
import com.company.portal.demo.entity.Workgroup;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SurveyDto {
    private Long id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private Integer minResponse;
    private Integer maxResponse;
    private Set<Workgroup> workgroups;
    private Set<Question> questions;
}
