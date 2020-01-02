package com.khrystyna.nerdysoft.controller;

import com.khrystyna.nerdysoft.exceptions.InvalidUserDetailsException;
import com.khrystyna.nerdysoft.exceptions.OccupiedEmailException;
import com.khrystyna.nerdysoft.exceptions.TaskNotFoundException;
import com.khrystyna.nerdysoft.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidUserDetailsException.class)
    ResponseEntity<String> handleInvalidUserDetailsException(InvalidUserDetailsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(OccupiedEmailException.class)
    ResponseEntity<String> handleOccupiedEmailException(OccupiedEmailException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(TaskNotFoundException.class)
    ResponseEntity<String> handleTaskNotFoundException(TaskNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}