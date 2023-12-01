package com.sparta.springtodolist.dto.restApi;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RestApiExceptionDto {
    private String errorMessage;
    private int statusCode;
}