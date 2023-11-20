package com.sparta.springtodolist.service;

import com.sparta.springtodolist.dto.BoardFinishRequestDto;
import com.sparta.springtodolist.dto.BoardRequestDto;
import com.sparta.springtodolist.dto.BoardResponseDto;
import com.sparta.springtodolist.entity.Board;
import com.sparta.springtodolist.entity.User;
import com.sparta.springtodolist.repository.BoardRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional(readOnly=true)
    public List<BoardResponseDto> getBoard() {
        return boardRepository.findAll().stream().map(BoardResponseDto::new).toList();
    }

    public BoardResponseDto createBoard(BoardRequestDto boardRequestDto, User user) {
        Board board = new Board(boardRequestDto,user);
        boardRepository.save(board);
        return new BoardResponseDto(board);
    }
    @Transactional(readOnly=true)
    public BoardResponseDto getBoardDetail(Long id) {
        return  new BoardResponseDto(boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("계시글이 없습니다.")));
    }

    @Transactional
    public BoardResponseDto updateBoard(Long id, BoardRequestDto boardRequestDto, User user) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("선택한 게시물이 존재하지 않습니다."));
        if(!board.getUser().getId().equals(user.getId())){
            throw new IllegalArgumentException("본인 이외에는 수정할수 없습니다.");
        }
        board.update(boardRequestDto);
        return new BoardResponseDto(board);
    }
    @Transactional
    public BoardResponseDto finishBoard(Long id, BoardFinishRequestDto boardRequestDto, User user) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("선택한 게시물이 존재하지 않습니다."));
        if(!board.getUser().getId().equals(user.getId())){
            throw new IllegalArgumentException("본인 이외에는 수정할수 없습니다.");
        }
        board.finish(boardRequestDto.isFinish());
        return new BoardResponseDto(board);
    }
}
