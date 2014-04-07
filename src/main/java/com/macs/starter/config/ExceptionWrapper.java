package com.macs.starter.config;

import com.macs.starter.model.BasicResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ExceptionWrapper {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BasicResponse<String>> exception(Exception e, HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<BasicResponse<String>>(new BasicResponse<String>("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
