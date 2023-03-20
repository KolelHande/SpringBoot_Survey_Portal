package com.company.portal.demo.service.impl;

import com.company.portal.demo.entity.User;
import com.company.portal.demo.mapper.user.UserMapper;
import com.company.portal.demo.payload.dto.UserDto;
import com.company.portal.demo.repository.UserRepository;
import com.company.portal.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public UserDto findByEmailOrUserName(String email, String userName){
        User user = userRepository.findByEmailOrUserName(email, userName).orElseThrow(()->
                new UsernameNotFoundException("User not found with username or email: "+ email + " " + userName));
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto findByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()->
                new UsernameNotFoundException("User not found with email: "+ email));
        return userMapper.userToUserDto(user);
    }

}
