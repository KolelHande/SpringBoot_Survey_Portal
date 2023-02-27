package com.company.portal.demo.payload.dto;

import com.company.portal.demo.util.GeneralDateDeserializer;
import com.company.portal.demo.util.GeneralDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSurveyResultDto {
    @JsonDeserialize(using = GeneralDateDeserializer.class)
    @JsonSerialize(using = GeneralDateSerializer.class)
    private Date responseDate;

    private Integer updatedResponseCount;

    private SurveyDto survey;

    private UserDto user;
}
