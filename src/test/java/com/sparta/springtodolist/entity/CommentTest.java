package com.sparta.springtodolist.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.sparta.springtodolist.dto.request.BoardRequestDto;
import com.sparta.springtodolist.dto.request.CommentRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommentTest {

    private final User user = new User("username", "password", UserRoleEnum.USER);
    private final BoardRequestDto boardRequestDto = BoardRequestDto.builder().title("테스트 타이틀")
        .detail("테스트 디테일").build();
    private final Board board = new Board(boardRequestDto, user);
    private final CommentRequestDto commentRequestDto = CommentRequestDto.builder()
        .detail("테스트 댓글 디테일").build();

    @Test
    @DisplayName("Comment 생성자 테스트")
    void Constructor() {
        Comment comment = new Comment(commentRequestDto, user, board);
        assertEquals(user, comment.getUser());
        assertEquals(board, comment.getBoard());
        assertEquals("테스트 댓글 디테일", comment.getDetail());
    }

    @Test
    void update() {
        Comment comment = new Comment(commentRequestDto, user, board);
        CommentRequestDto commentRequestDto2 = CommentRequestDto.builder()
            .detail("테스트").build();
        comment.update(commentRequestDto2);
        assertEquals("테스트", comment.getDetail());
    }

    @Test
    void getId() {
        Comment comment = new Comment(commentRequestDto, user, board);
        assertNull(comment.getId());
    }

    @Test
    void getUser() {
        Comment comment = new Comment(commentRequestDto, user, board);
        assertEquals(user, comment.getUser());
    }

    @Test
    void getBoard() {
        Comment comment = new Comment(commentRequestDto, user, board);
        assertEquals(board, comment.getBoard());
    }

    @Test
    void getDetail() {
        Comment comment = new Comment(commentRequestDto, user, board);
        assertEquals("테스트 댓글 디테일", comment.getDetail());
    }

    @Test
    void setId() {
        Comment comment = new Comment(commentRequestDto, user, board);
        comment.setId(1L);
        assertEquals(1L, comment.getId());
    }

    @Test
    void setUser() {
        Comment comment = new Comment(commentRequestDto, user, board);
        User user2 = new User("username2", "password2", UserRoleEnum.ADMIN);
        comment.setUser(user2);
        assertEquals(user2, comment.getUser());
    }

    @Test
    void setBoard() {
        Comment comment = new Comment(commentRequestDto, user, board);
        User user2 = new User("username2", "password2", UserRoleEnum.ADMIN);
        BoardRequestDto boardRequestDto2 = BoardRequestDto.builder().title("테스트 타이틀2")
            .detail("테스트 디테일2").build();
        Board board2 = new Board(boardRequestDto2, user2);
        comment.setBoard(board2);
        assertEquals(board2, comment.getBoard());
    }

    @Test
    void setDetail() {
        Comment comment = new Comment(commentRequestDto, user, board);
        comment.setDetail("테스트2");
        assertEquals("테스트2", comment.getDetail());
    }
}