package com.sparta.springtodolist.service;

import com.sparta.springtodolist.dto.request.BoardRequestDto;
import com.sparta.springtodolist.dto.response.BoardResponseDto;
import com.sparta.springtodolist.entity.Board;
import com.sparta.springtodolist.entity.User;
import com.sparta.springtodolist.repository.BoardRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoradService {

    private final BoardRepository boardRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BoardResponseDto> getBoard() {
        List<BoardResponseDto> list = boardRepository.findAll().stream().map(BoardResponseDto::new)
            .toList();
        if (list.isEmpty()) {
            throw new NullPointerException("게시글이 없습니다.");
        }
        return list;
    }

    @Override
    public BoardResponseDto createBoard(BoardRequestDto boardRequestDto, User user) {
        Board board = new Board(boardRequestDto, user);
        boardRepository.save(board);
        return new BoardResponseDto(board);
    }

    @Override
    @Transactional(readOnly = true)
    public BoardResponseDto getBoardDetail(Long id) {
        return new BoardResponseDto(
            boardRepository.findById(id).orElseThrow(() -> new NullPointerException("게시글이 없습니다.")));
    }

    @Override
    @Transactional
    public BoardResponseDto updateBoard(Long id, BoardRequestDto boardRequestDto, User user) {
        Board board = boardRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("선택한 게시물이 존재하지 않습니다."));
        if (!board.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("본인 이외에는 수정할수 없습니다.");
        }
        board.update(boardRequestDto);
        return new BoardResponseDto(board);
    }

    @Override
    @Transactional
    public BoardResponseDto finishBoard(Long id, User user) {
        Board board = boardRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("선택한 게시물이 존재하지 않습니다."));
        if (!board.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("본인 이외에는 수정할수 없습니다.");
        }
        board.finish();
        return new BoardResponseDto(board);
    }

    @Override
    @Transactional
    public String deleteBoard(Long id, User user) {
        Board board = boardRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("선택한 게시물이 존재하지 않습니다."));
        if (!board.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("본인 이외에는 수정할수 없습니다.");
        }
        boardRepository.delete(board);
        return "삭제 완료";
    }
}
