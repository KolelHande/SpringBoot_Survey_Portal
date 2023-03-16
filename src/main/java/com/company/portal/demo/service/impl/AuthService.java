package com.company.portal.demo.service.impl;

import com.company.portal.demo.entity.Role;
import com.company.portal.demo.entity.User;
import com.company.portal.demo.enums.OperationTypeEnum;
import com.company.portal.demo.exception.RTBusinessException;
import com.company.portal.demo.exception.ResourceNotFoundException;
import com.company.portal.demo.payload.request.LoginRequest;
import com.company.portal.demo.payload.request.RegisterRequest;
import com.company.portal.demo.payload.request.mail.MailRequest;
import com.company.portal.demo.repository.RoleRepository;
import com.company.portal.demo.repository.UserRepository;
import com.company.portal.demo.security.JwtTokenProvider;
import com.company.portal.demo.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final NotificationService notificationService;

    public String login(LoginRequest request){
        Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(request.getUsernameOrEmail(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(authentication);
    }

    public String register(RegisterRequest request){

        if(Boolean.TRUE.equals(userRepository.existsByEmailOrUserName(request.getEmail(), request.getUserName()))){
            throw new RTBusinessException("User is already exist");
        }

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("USER").orElseThrow(() -> new ResourceNotFoundException("User Role does not found"));
        roles.add(userRole);

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .userName(request.getUserName())
                .surname(request.getSurname())
                .birthDate(request.getBirthDate())
                .phoneNumber(request.getPhoneNumber())
                .encryptedPassword(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .build();

        userRepository.save(user);
        sendMail(request.getEmail(), OperationTypeEnum.REGISTERED_USER_INFO_SEND_EMAIL_TO_ADMIN);
        return "Register successfully";
    }

    public void resetPassword(String resetToken, String newPassword) {
        User user = userRepository.findByResetToken(resetToken);
        if (user == null) {
            throw new RTBusinessException("Invalid token");
        }

        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setEncryptedPassword(encodedPassword);
        user.setResetToken(null);
        userRepository.save(user);
    }


    private void sendMail(String email, OperationTypeEnum operation) {
        MailRequest mailRequest=new MailRequest();
        mailRequest.setOperationType(operation);
        notificationService.sendNotification(mailRequest);
    }
}