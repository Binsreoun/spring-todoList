package com.sparta.springtodolist.dto.response;

import com.sparta.springtodolist.entity.Board;
import lombok.Getter;

@Getter
public class BoardResponseDto {

    private final Long id;
    private final String username;
    private final String title;
    private final String detail;
    private final boolean finish;

    public BoardResponseDto(Board Board) {
        this.id = Board.getId();
        this.username = Board.getUser().getUsername();
        this.title = Board.getTitle();
        this.detail = Board.getDetail();
        this.finish = Board.isFinish();
    }
}