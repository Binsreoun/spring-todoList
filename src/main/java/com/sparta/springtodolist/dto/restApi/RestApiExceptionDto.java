package com.sparta.springtodolist.dto.restApi;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestApiExceptionDto {

    private String errorMessage;
    private int statusCode;
}