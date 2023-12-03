package com.sparta.springtodolist.dto.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommentRequestDto {

    private String detail;
}
