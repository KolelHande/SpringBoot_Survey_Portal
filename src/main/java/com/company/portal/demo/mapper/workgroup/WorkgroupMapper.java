package com.company.portal.demo.mapper.workgroup;


import com.company.portal.demo.entity.Workgroup;
import com.company.portal.demo.payload.request.workgroup.CreateWorkgroupRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface WorkgroupMapper {
    Workgroup workgroupRequestToWorkgroup(CreateWorkgroupRequest request);

}
