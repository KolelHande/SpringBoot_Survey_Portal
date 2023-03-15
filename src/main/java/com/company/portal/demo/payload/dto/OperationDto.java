package com.company.portal.demo.payload.dto;

import com.company.portal.demo.enums.OperationTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OperationDto {
    private Long id;
    private OperationTypeEnum name;
}
