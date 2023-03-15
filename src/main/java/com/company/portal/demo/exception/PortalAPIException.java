package com.company.portal.demo.exception;

import org.springframework.http.HttpStatus;

public class PortalAPIException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public PortalAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public PortalAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}