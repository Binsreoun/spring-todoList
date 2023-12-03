package com.sparta.springtodolist.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.sparta.springtodolist.dto.request.BoardRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    @DisplayName("Board 생성자 테스트")
    void ConstructorTest() {
        User user = new User("username", "password", UserRoleEnum.USER);
        BoardRequestDto boardRequestDto = BoardRequestDto.builder().title("테스트 타이틀")
            .detail("테스트 디테일").build();
        Board board = new Board(boardRequestDto, user);

        assertEquals(user, board.getUser());
        assertEquals("테스트 타이틀", board.getTitle());
        assertEquals("테스트 디테일", board.getDetail());
        assertFalse(board.isFinish());
    }

}