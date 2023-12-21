package com.sparta.springtodolist.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;

@JsonInclude(Include.NON_NULL)
@Builder
public record ErrorResponse<T>(
    String code,
    String message,
    T data
) {

}
