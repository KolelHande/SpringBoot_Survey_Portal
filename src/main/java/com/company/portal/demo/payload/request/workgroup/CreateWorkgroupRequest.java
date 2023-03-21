package com.company.portal.demo.payload.request.workgroup;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CreateWorkgroupRequest {

    @NotEmpty(message = "{workgroup.name.not.empty}")
    private String name;

    private Long id;


}
