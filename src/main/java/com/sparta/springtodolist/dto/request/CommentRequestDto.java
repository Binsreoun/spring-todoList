package com.sparta.springtodolist.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequestDto {

    private String detail;

    @Builder
    public CommentRequestDto(String detail) {
        this.detail = detail;
    }
}
