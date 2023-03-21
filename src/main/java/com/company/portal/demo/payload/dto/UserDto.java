package com.company.portal.demo.payload.dto;

import com.company.portal.demo.entity.Workgroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private Long id;

    private String name;

    private String surname;

    private String userName;

    private String phoneNumber;

    private String email;

    private String resetToken;

    private Set<Workgroup> workgroups;

}
