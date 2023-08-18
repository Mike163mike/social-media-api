package com.effectivemobile.socialmediaapi.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.effectivemobile.socialmediaapi.exception.AppException;

@RestControllerAdvice
public class AbstractRestController {

    @ExceptionHandler({AppException.class})
    public ResponseEntity<String> exceptionHandler(AppException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
