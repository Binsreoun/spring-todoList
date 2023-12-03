package com.sparta.springtodolist.dto.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.sparta.springtodolist.dto.request.BoardRequestDto;
import com.sparta.springtodolist.entity.Board;
import com.sparta.springtodolist.entity.User;
import com.sparta.springtodolist.entity.UserRoleEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardResponseDtoTest {

    @Test
    @DisplayName("BoardResponseDto 생성자 테스트")
    void ConstructorTest() {
        User user = new User("username", "password", UserRoleEnum.USER);
        BoardRequestDto boardRequestDto = BoardRequestDto.builder().title("테스트 타이틀")
            .detail("테스트 디테일").build();
        Board board = new Board(boardRequestDto, user);
        BoardResponseDto responseDto = new BoardResponseDto(board);

        assertEquals("username", responseDto.getUsername());
        assertEquals("테스트 타이틀", responseDto.getTitle());
        assertEquals("테스트 디테일", responseDto.getDetail());
        assertFalse(responseDto.isFinish());
    }
}