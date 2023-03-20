package com.company.portal.demo.service;

import com.company.portal.demo.payload.request.auth.LoginRequest;
import com.company.portal.demo.payload.request.auth.RegisterRequest;

public interface AuthService {
    String login(LoginRequest request);

    String register(RegisterRequest request);

    void resetPassword(String resetToken, String newPassword);

    void sendVerificationCodeToUserForResetPassword(String email);
}
