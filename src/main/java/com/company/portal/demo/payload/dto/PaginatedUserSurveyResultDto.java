package com.company.portal.demo.payload.dto;

import com.company.portal.demo.payload.pagination.PaginationDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class PaginatedUserSurveyResultDto extends PaginationDto {
    List<UserSurveyResultDto> userSurveyResults;
}
