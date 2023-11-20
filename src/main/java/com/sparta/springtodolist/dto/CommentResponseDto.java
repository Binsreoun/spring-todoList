package com.sparta.springtodolist.dto;

import com.sparta.springtodolist.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {

    private final String username;
    private final String detail;

    public CommentResponseDto(Comment comment) {

        this.username = comment.getUser().getUsername();
        this.detail = comment.getDetail();

    }
}
