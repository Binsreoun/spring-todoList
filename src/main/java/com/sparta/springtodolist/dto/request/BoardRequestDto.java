package com.sparta.springtodolist.dto.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BoardRequestDto {

    private String title;
    private String detail;
}
