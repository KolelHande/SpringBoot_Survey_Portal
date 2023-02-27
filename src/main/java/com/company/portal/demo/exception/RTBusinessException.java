package com.company.portal.demo.exception;

public class RTBusinessException extends RuntimeException implements HasParametricExceptionMessage {
    private static final long serialVersionUID = -1959834592522354897L;
    private String code = "";
    private String[] messageParams;

    public RTBusinessException() {
    }

    public RTBusinessException(String... messageParams) {
        this.messageParams = messageParams;
    }

    public RTBusinessException messageParams(String... messageParams) {
        this.messageParams = messageParams;
        return this;
    }

    public RTBusinessException(String message) {
        super(message);
    }

    public RTBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public RTBusinessException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String[] getMessageParams() {
        return this.messageParams;
    }

    public void setMessageParams(String... messageParams) {
        this.messageParams = messageParams;
    }
}