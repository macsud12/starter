package com.macs.starter.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

/**
 * Error Code
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED.value(), 10001, "Not Authorized"),
    FORBIDDEN(HttpStatus.FORBIDDEN.value(), 10003, "Forbidden");

    private int errorCode;
    private String message;
    private int status;

    ErrorCode(int status, int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
        this.status = status;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
