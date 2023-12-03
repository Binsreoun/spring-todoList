package com.sparta.springtodolist.dto.response;

import com.sparta.springtodolist.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {

    private final Long id;
    private final String username;
    private final String detail;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.username = comment.getUser().getUsername();
        this.detail = comment.getDetail();

    }
}
