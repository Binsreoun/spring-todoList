package com.sparta.springtodolist.dto.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.sparta.springtodolist.dto.request.BoardRequestDto;
import com.sparta.springtodolist.dto.request.CommentRequestDto;
import com.sparta.springtodolist.entity.Board;
import com.sparta.springtodolist.entity.Comment;
import com.sparta.springtodolist.entity.User;
import com.sparta.springtodolist.entity.UserRoleEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommentResponseDtoTest {

    private final User user = new User("username", "password", UserRoleEnum.USER);
    private final BoardRequestDto boardRequestDto = BoardRequestDto.builder().title("테스트 타이틀")
        .detail("테스트 디테일").build();

    private final CommentRequestDto commentRequestDto = CommentRequestDto.builder()
        .detail("테스트 댓글 디테일").build();
    private final Board board = new Board(boardRequestDto, user);
    private final Comment comment = new Comment(commentRequestDto, user, board);
    private final CommentResponseDto responseDto = new CommentResponseDto(comment);

    @Test
    @DisplayName("CommentResponseDto 생성자 테스트")
    void builder() {
        assertNull(responseDto.getId());
        assertEquals("username", responseDto.getUsername());
        assertEquals("테스트 댓글 디테일", responseDto.getDetail());
    }

    @Test
    void getId() {
        assertNull(responseDto.getId());
    }

    @Test
    void getUsername() {
        assertEquals("username", responseDto.getUsername());
    }

    @Test
    void getDetail() {
        assertEquals("테스트 댓글 디테일", responseDto.getDetail());
    }
}