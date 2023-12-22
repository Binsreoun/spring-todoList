package com.sparta.springtodolist.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sparta.springtodolist.dto.request.BoardRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    private final User user = new User("username", "password", UserRoleEnum.USER);
    private final BoardRequestDto boardRequestDto = BoardRequestDto.builder().title("테스트 타이틀")
        .detail("테스트 디테일").build();

    @Test
    @DisplayName("Board 생성자 테스트")
    void Constructor() {
        Board board = new Board(boardRequestDto, user);
        assertEquals(user, board.getUser());
        assertEquals("테스트 타이틀", board.getTitle());
        assertEquals("테스트 디테일", board.getDetail());
        assertFalse(board.isFinish());
    }

    @Test
    void update() {
        Board board = new Board(boardRequestDto, user);
        BoardRequestDto boardRequestDto2 = BoardRequestDto.builder().title("테스트 타이틀2")
            .detail("테스트 디테일2").build();
        board.update(boardRequestDto2);
        assertEquals("테스트 타이틀2", board.getTitle());
        assertEquals("테스트 디테일2", board.getDetail());
    }

    @Test
    void finish() {
        Board board = new Board(boardRequestDto, user);
        board.finish();
        assertTrue(board.isFinish());
    }

    @Test
    void getId() {
        Board board = new Board(boardRequestDto, user);
        assertNull(board.getId());
    }

    @Test
    void getUser() {
        Board board = new Board(boardRequestDto, user);
        assertEquals(user, board.getUser());
    }

    @Test
    void getTitle() {
        Board board = new Board(boardRequestDto, user);
        assertEquals("테스트 타이틀", board.getTitle());
    }

    @Test
    void getDetail() {
        Board board = new Board(boardRequestDto, user);
        assertEquals("테스트 디테일", board.getDetail());
    }

    @Test
    void isFinish() {
        Board board = new Board(boardRequestDto, user);
        assertFalse(board.isFinish());
    }

    @Test
    void setId() {
        Board board = new Board(boardRequestDto, user);
        board.setId(1L);
        assertEquals(1L, board.getId());
    }

    @Test
    void setUser() {
        Board board = new Board(boardRequestDto, user);
        User user2 = new User("username2", "password2", UserRoleEnum.ADMIN);
        board.setUser(user2);
        assertEquals(user2, board.getUser());

    }

    @Test
    void setTitle() {
        Board board = new Board(boardRequestDto, user);
        board.setTitle("테스트 타이틀2");
        assertEquals("테스트 타이틀2", board.getTitle());
    }

    @Test
    void setDetail() {
        Board board = new Board(boardRequestDto, user);
        board.setDetail("테스트 디테일2");
        assertEquals("테스트 디테일2", board.getDetail());
    }

    @Test
    void setTrueFinish() {
        Board board = new Board(boardRequestDto, user);
        board.setFinish(true);
        assertTrue(board.isFinish());
    }
    @Test
    void setFinish() {
        Board board = new Board(boardRequestDto, user);
        board.finish();
        assertTrue(board.isFinish());
        board.finish();
        assertFalse(board.isFinish());
    }
}