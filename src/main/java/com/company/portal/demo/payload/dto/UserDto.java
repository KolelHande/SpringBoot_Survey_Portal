package com.company.portal.demo.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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
}
