package com.sparta.springtodolist.dto.restApi;

import lombok.Builder;
import lombok.Setter;

@Setter
public class RestApiResponseDto {
    private String message;
    private int statusCode;
    private Object data;
}
