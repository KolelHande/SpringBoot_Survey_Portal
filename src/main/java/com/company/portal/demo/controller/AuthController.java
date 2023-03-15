package com.company.portal.demo.controller;

import com.company.portal.demo.enums.OperationTypeEnum;
import com.company.portal.demo.exception.ResourceNotFoundException;
import com.company.portal.demo.payload.base.BaseResponse;
import com.company.portal.demo.payload.dto.AuthResponse;
import com.company.portal.demo.payload.request.LoginRequest;
import com.company.portal.demo.payload.request.RegisterRequest;
import com.company.portal.demo.payload.request.mail.MailRequest;
import com.company.portal.demo.service.NotificationService;
import com.company.portal.demo.service.impl.AuthService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/api/auths")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private Logger logger= LoggerFactory.getLogger(AuthController.class);
    private final NotificationService notificationService;

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
        try {

            sendMail(request.getEmail(), OperationTypeEnum.REGISTERED_USER_INFO_SEND_EMAIL_TO_ADMIN);

            String response = authService.register(request);
            return new ResponseEntity<>(new BaseResponse<>(response), HttpStatus.CREATED);

        } catch (MailException e) {
            logger.info(String.format("Error sending mail: %s", e.getMessage()));
            return new ResponseEntity<>(new BaseResponse<>("An error occurred while processing your request. Please try again later.")
                    , HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @PostMapping("/reset")
    public ResponseEntity<BaseResponse<String>> resetPassword(@RequestParam("email") String email) {
        try {
            notificationService.sendPasswordResetMail(email);
            return new ResponseEntity<>(new BaseResponse<>("Password reset link sent successfully"), HttpStatus.OK);
        } catch (MessagingException e) {
            return new ResponseEntity<>(new BaseResponse<>("Error sending password reset link"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/reset/new")
    public ResponseEntity<BaseResponse<String>> setNewPassword(@RequestParam("token") String token,
                                                               @RequestParam("newPassword") String newPassword) {
        try {
            authService.resetPassword(token, newPassword);
            return new ResponseEntity<>(new BaseResponse<>("Password reset successfully"), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new BaseResponse<>("Invalid token"), HttpStatus.BAD_REQUEST);
        }
    }

    private void sendMail(String email, OperationTypeEnum operation) {
        MailRequest mailRequest=new MailRequest();
        mailRequest.setOperationType(operation);
        notificationService.sendNotification(mailRequest);
    }

}