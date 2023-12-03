package com.sparta.springtodolist.service;

import com.sparta.springtodolist.dto.request.CommentRequestDto;
import com.sparta.springtodolist.dto.response.CommentResponseDto;
import com.sparta.springtodolist.entity.Board;
import com.sparta.springtodolist.entity.Comment;
import com.sparta.springtodolist.entity.User;
import com.sparta.springtodolist.repository.BoardRepository;
import com.sparta.springtodolist.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public CommentResponseDto createComment(Long id, CommentRequestDto CommentRequestDto,
        User user) {
        Board board = boardRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("선택한 게시물이 존재하지 않습니다."));
        Comment comment = new Comment(CommentRequestDto, user, board);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long id, CommentRequestDto commentRequestDto,
        User user) {
        Comment comment = commentRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("선택한 댓글 존재하지 않습니다."));
        if (!comment.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("본인 이외에는 수정할수 없습니다.");
        }
        comment.update(commentRequestDto);
        return new CommentResponseDto(comment);
    }

    public Long deleteComment(Long id, User user) {
        Comment comment = commentRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("선택한 댓글 존재하지 않습니다."));
        if (!comment.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("본인 이외에는 수정할수 없습니다.");
        }
        commentRepository.delete(comment);
        return id;
    }

    @Transactional
    public List<CommentResponseDto> viewComment(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("선택한 게시물이 존재하지 않습니다."));
        return commentRepository.findByBoard(board).stream().map(CommentResponseDto::new).toList();
    }
}
