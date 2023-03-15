package com.company.portal.demo.mapper.operation;

import com.company.portal.demo.entity.Operation;
import com.company.portal.demo.payload.dto.OperationDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface OperationMapper {

    OperationDto operationToOperationDto (Operation operation);
}
