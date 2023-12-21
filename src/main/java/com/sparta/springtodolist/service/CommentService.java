package com.sparta.springtodolist.service;

import com.sparta.springtodolist.dto.request.CommentRequestDto;
import com.sparta.springtodolist.dto.response.CommentResponseDto;
import com.sparta.springtodolist.entity.User;
import java.util.List;

public interface CommentService {

    CommentResponseDto createComment(Long id, CommentRequestDto CommentRequestDto, User user);

    CommentResponseDto updateComment(Long id, CommentRequestDto commentRequestDto, User user);

    Long deleteComment(Long id, User user);

    List<CommentResponseDto> viewComment(Long id);
}
