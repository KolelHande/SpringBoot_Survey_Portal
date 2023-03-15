package com.company.portal.demo.service.impl;

import com.company.portal.demo.entity.Operation;
import com.company.portal.demo.enums.OperationTypeEnum;
import com.company.portal.demo.exception.ResourceNotFoundException;
import com.company.portal.demo.mapper.operation.OperationMapper;
import com.company.portal.demo.payload.dto.OperationDto;
import com.company.portal.demo.repository.OperationRepository;
import com.company.portal.demo.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OperationServiceImpl implements OperationService {

    private final OperationRepository operationRepository;
    private final OperationMapper operationMapper;

    @Override
    public OperationDto getOperationByName(OperationTypeEnum operationTypeEnum){

        Operation operation = operationRepository.findByName(operationTypeEnum).orElseThrow(() -> new ResourceNotFoundException("Operation is not found"));

        return operationMapper.operationToOperationDto(operation);

    }

}
