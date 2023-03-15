package com.company.portal.demo.service;

import com.company.portal.demo.enums.OperationTypeEnum;
import com.company.portal.demo.payload.dto.OperationDto;

public interface OperationService {
    OperationDto getOperationByName(OperationTypeEnum operationTypeEnum);
}
