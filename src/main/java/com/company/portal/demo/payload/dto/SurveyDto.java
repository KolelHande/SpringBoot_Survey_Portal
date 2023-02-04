package com.company.portal.demo.payload.dto;

import com.company.portal.demo.entity.Question;
import com.company.portal.demo.util.GeneralDateDeserializer;
import com.company.portal.demo.util.GeneralDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @JsonDeserialize(using = GeneralDateDeserializer.class)
    @JsonSerialize(using = GeneralDateSerializer.class)
    private Date startDate;

    @JsonDeserialize(using = GeneralDateDeserializer.class)
    @JsonSerialize(using = GeneralDateSerializer.class)
    private Date endDate;

    private Integer minResponse;

    private Integer maxResponse;

    //private Set<Workgroup> workgroups;

    private Set<Question> questions;

}
