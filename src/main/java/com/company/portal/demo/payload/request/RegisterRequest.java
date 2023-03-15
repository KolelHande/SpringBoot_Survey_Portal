package com.company.portal.demo.payload.request;

import lombok.Data;

import java.util.Date;

@Data
public class RegisterRequest {
    private String name;
    private String userName;
    private String email;
    private String password;
    private String surname;
    private String phoneNumber;
    private Date birthDate;
}