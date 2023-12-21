package com.sparta.springtodolist.service;

import com.sparta.springtodolist.dto.request.BoardRequestDto;
import com.sparta.springtodolist.dto.response.BoardResponseDto;
import com.sparta.springtodolist.entity.User;
import java.util.List;

public interface BoradService {

    List<BoardResponseDto> getBoard();

    BoardResponseDto createBoard(BoardRequestDto boardRequestDto, User user);

    public BoardResponseDto getBoardDetail(Long id);

    BoardResponseDto updateBoard(Long id, BoardRequestDto boardRequestDto, User user);

    BoardResponseDto finishBoard(Long id, User user);

    String deleteBoard(Long id, User user);
}
