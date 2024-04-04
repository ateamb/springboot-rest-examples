package com.javabanters.demorest.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException ex) {
        StudentErrorResponse response = new StudentErrorResponse();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(ex.getMessage());
        response.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
