package com.company.portal.demo.payload.request;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {

    private String usernameOrEmail;
    private String password;
}