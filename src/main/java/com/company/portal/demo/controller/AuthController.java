package com.company.portal.demo.controller;

import com.company.portal.demo.payload.base.BaseResponse;
import com.company.portal.demo.payload.dto.AuthResponse;
import com.company.portal.demo.payload.request.auth.LoginRequest;
import com.company.portal.demo.payload.request.auth.RegisterRequest;
import com.company.portal.demo.payload.request.auth.ResetPasswordRequest;
import com.company.portal.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auths")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<AuthResponse>> login(@RequestBody LoginRequest request){

        String token = authService.login(request);
        AuthResponse authResponse = AuthResponse.builder()
                .accessToken(token)
                .build();

        return ResponseEntity.ok(new BaseResponse<>(authResponse));
    }

    @PostMapping("/register")
    public ResponseEntity<BaseResponse<String>> register(@RequestBody RegisterRequest request){

        String response = authService.register(request);
        return new ResponseEntity<>(new BaseResponse<>(response), HttpStatus.CREATED);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<BaseResponse<String>> resetPassword(@RequestParam("email") String email) {

        authService.sendVerificationCodeToUserForResetPassword(email);
        return new ResponseEntity<>(new BaseResponse<>("Password reset link sent successfully"), HttpStatus.OK);
    }

    @PostMapping("/reset-password/new")
    public ResponseEntity<BaseResponse<String>> setNewPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        authService.resetPassword(resetPasswordRequest.getToken(), resetPasswordRequest.getNewPassword());
        return new ResponseEntity<>(new BaseResponse<>("Password reset successfully"), HttpStatus.OK);
    }
}