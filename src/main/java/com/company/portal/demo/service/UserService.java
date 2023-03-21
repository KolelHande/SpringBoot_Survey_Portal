package com.company.portal.demo.service;

import com.company.portal.demo.entity.User;
import com.company.portal.demo.payload.dto.UserDto;

public interface UserService {
    User getUserById(Long id);

    UserDto findByEmailOrUserName(String email, String userName);

    UserDto findByEmail(String email);

    UserDto getAuthenticatedUser();
}
