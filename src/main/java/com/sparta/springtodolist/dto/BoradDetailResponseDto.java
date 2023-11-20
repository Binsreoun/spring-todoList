package com.sparta.springtodolist.dto;

import com.sparta.springtodolist.entity.Board;

import java.util.List;

public class BoradDetailResponseDto {
    private final Long id;
    private final String username;
    private final String title;
    private final String detail;
    private final boolean finish;

    private final List<CommentResponseDto> comment;

    public BoradDetailResponseDto(Board Board,List<CommentResponseDto> comment ) {
        this.id= Board.getId();
        this.username = Board.getUser().getUsername();
        this.title=Board.getTitle();
        this.detail=Board.getDetail();
        this.finish=Board.isFinish();
        this.comment=comment;
    }
}
