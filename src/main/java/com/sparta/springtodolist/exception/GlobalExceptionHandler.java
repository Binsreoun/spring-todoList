package com.sparta.springtodolist.exception;

import com.sparta.springtodolist.dto.restApi.RestApiExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<RestApiExceptionDto> illegalArgumentExceptionHandler(IllegalArgumentException ex) {
        RestApiExceptionDto restApiExceptionDto = new RestApiExceptionDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(
                // HTTP body
                restApiExceptionDto,
                // HTTP status code
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<RestApiExceptionDto> nullPointerExceptionHandler(NullPointerException ex) {
        RestApiExceptionDto restApiExceptionDto = new RestApiExceptionDto(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(
                // HTTP body
                restApiExceptionDto,
                // HTTP status code
                HttpStatus.NOT_FOUND
        );
    }

}