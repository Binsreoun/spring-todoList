package com.sparta.springtodolist.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sparta.springtodolist.dto.request.BoardRequestDto;
import com.sparta.springtodolist.dto.request.CommentRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommentTest {

    @Test
    @DisplayName("Comment 생성자 테스트")
    void ConstructorTest() {
        User user = new User("username", "password", UserRoleEnum.USER);
        BoardRequestDto boardRequestDto = BoardRequestDto.builder().title("테스트 타이틀")
            .detail("테스트 디테일").build();
        Board board = new Board(boardRequestDto, user);
        CommentRequestDto commentRequestDto = CommentRequestDto.builder()
            .detail("테스트 댓글 디테일").build();
        Comment comment = new Comment(commentRequestDto, user, board);

        assertEquals(user, comment.getUser());
        assertEquals(board, comment.getBoard());
        assertEquals("테스트 댓글 디테일", comment.getDetail());
    }
}