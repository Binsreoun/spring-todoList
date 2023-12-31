package com.sparta.springtodolist.common.exception;

import com.sparta.springtodolist.dto.response.ErrorResponse;
import com.sparta.springtodolist.dto.restApi.RestApiExceptionDto;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<?> handleException(ServiceException ex) {
        ErrorCode code = ex.getCode();
        ErrorResponse<Object> response = ErrorResponse.builder()
            .code(code.getCode())
            .message(code.getMessage())
            .build();
        return ResponseEntity.status(code.getStatus())
            .body(response);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<RestApiExceptionDto> illegalArgumentExceptionHandler(
        IllegalArgumentException ex) {
        RestApiExceptionDto restApiExceptionDto = new RestApiExceptionDto(ex.getMessage(),
            HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(
            // HTTP body
            restApiExceptionDto,
            // HTTP status code
            HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<RestApiExceptionDto> nullPointerExceptionHandler(
        NullPointerException ex) {
        RestApiExceptionDto restApiExceptionDto = new RestApiExceptionDto(ex.getMessage(),
            HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(
            // HTTP body
            restApiExceptionDto,
            // HTTP status code
            HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<RestApiExceptionDto> validationExceptionHandler(ValidationException ex) {
        RestApiExceptionDto restApiExceptionDto = new RestApiExceptionDto(ex.getMessage(),
            HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(
            // HTTP body
            restApiExceptionDto,
            // HTTP status code
            HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<RestApiExceptionDto> MethodArgumentNotValidExceptionHandler(
        MethodArgumentNotValidException ex) {
        RestApiExceptionDto restApiExceptionDto = new RestApiExceptionDto(
            "회원 가입 정보 오류, 형싱에 맞게 가입해 주세요",
            HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(
            // HTTP body
            restApiExceptionDto,
            // HTTP status code
            HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<RestApiExceptionDto> HttpMessageNotReadableExceptionHandler(
        HttpMessageNotReadableException ex) {
        RestApiExceptionDto restApiExceptionDto = new RestApiExceptionDto(
            "HttpMessageNotReadableException",
            HttpStatus.NOT_FOUND.value());
        ex.printStackTrace();
        return new ResponseEntity<>(
            // HTTP body
            restApiExceptionDto,
            // HTTP status code
            HttpStatus.NOT_FOUND
        );
    }


}